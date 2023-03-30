package com.strangenaut.timetablebstu.feature_timetable.data.repository

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.strangenaut.timetablebstu.feature_timetable.data.TimetableService
import com.strangenaut.timetablebstu.feature_timetable.data.model.TimetableDTO
import com.strangenaut.timetablebstu.feature_timetable.data.util.TimetableDTOMapper
import com.strangenaut.timetablebstu.feature_timetable.data.util.XmlConverter
import com.strangenaut.timetablebstu.feature_timetable.domain.model.Timetable
import com.strangenaut.timetablebstu.feature_timetable.domain.repository.TimetableRepository
import com.strangenaut.timetablebstu.feature_timetable.util.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File
import javax.inject.Inject

class TimetableRepositoryImpl @Inject constructor(
    private val timetableService: TimetableService,
    private val mapper: TimetableDTOMapper,
    private val internalFile: File,
    private val xmlConverter: XmlConverter,
    private val sharedPreferences: SharedPreferences,
) : TimetableRepository {

    private var _timetable = MutableLiveData(Timetable())
    override val timetable: LiveData<Timetable> = _timetable

    private var timetableJson: String = ""

    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    // Каждый раз, когда на сайте выкладывают новое расписание (даже если что - то меняет в старом)
    // - название файла с расписанием меняется. Например, с "TimetableGroup32.xml" на
    // "TimetableGroup33.xml". Ну, по факту, просто меняется номер в конце названия. Причём, старый
    // файл перестаёт быть доступным к скачиванию далеко не каждый раз, когда выкладывают новый -
    // фактически на сайте постоянно доступно к скачиванию несколько разных файлов с расписанием.
    // Поэтому, сначала нужно получить первое из тех, которые можно скачать, а потом - перебирать
    // все доступные до последнего.
    override suspend fun loadTimetableFromNetwork(): Timetable {
        var fileNumber = loadUserSettings("fileNumber", 0) as Int
        var xmlString = ""

        // Получение первой работающей версии файла с вебсайта
        while (xmlString.isEmpty()) {
            try {
                xmlString = timetableService.getTimetableXml(fileNumber).string()
            } catch (e: Exception) {
                Log.e(TAG, "Error getting timetable.xml: ${e.message}")
                Log.d(TAG, "Trying to get timetable with the new file number: ${++fileNumber}")
            }
        }

        // Получение последней работающей версии файла с вебсайта
        var tempXmlString = xmlString
        while (xmlString.isNotEmpty()) {
            Log.d(TAG, "Trying to get timetable with the new file number: ${++fileNumber}")

            xmlString = try {
                tempXmlString = timetableService.getTimetableXml(fileNumber).string()
                tempXmlString
            } catch (e: Exception) {
                Log.e(TAG, "Error getting timetable.xml: ${e.message}")
                fileNumber--
                ""
            }
        }
        xmlString = tempXmlString
        Log.d(TAG, "Resulting file number: $fileNumber")
        saveUserSettings("fileNumber", fileNumber)

        val domainModel = withContext(Dispatchers.Default) {
            timetableJson = xmlConverter.convertXmlToJson(xmlString)

            val timetableDto = json.decodeFromString<TimetableDTO>(timetableJson)
            mapper.mapToDomainModel(timetableDto)
        }

        _timetable.value = domainModel
        return _timetable.value ?: Timetable()
    }

    override suspend fun loadTimetableFromInternalStorage(): Timetable {
        timetableJson = if (internalFile.exists())
            internalFile.readText()
        else
            return Timetable()

        val domainModel = withContext(Dispatchers.Default) {
            val timetableDto = json.decodeFromString<TimetableDTO>(timetableJson)
            mapper.mapToDomainModel(timetableDto)
        }

        _timetable.value = domainModel
        return _timetable.value ?: Timetable()
    }

    override suspend fun saveTimetableToJson() {
        if (timetableJson.isNotEmpty() && timetableJson != "null") {
            internalFile.writeText(timetableJson)
        }
    }

    override fun loadUserSettings(key: String, defaultValue: Any): Any? {
        return when (defaultValue) {
            is Int -> sharedPreferences.getInt(key, defaultValue)
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue)
            is String -> sharedPreferences.getString(key, defaultValue)
            else -> null
        }
    }

    override suspend fun saveUserSettings(key: String, value: Any) {
        sharedPreferences.edit().apply {
            when (value) {
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is String -> putString(key, value)
            }
            apply()
        }
    }
}