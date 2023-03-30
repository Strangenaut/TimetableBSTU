package com.strangenaut.timetablebstu.feature_timetable.data

import com.strangenaut.timetablebstu.feature_timetable.util.GROUPS_TIMETABLE_URL_FILENAME_TEMPLATE
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface TimetableService {

    @GET(GROUPS_TIMETABLE_URL_FILENAME_TEMPLATE)
    suspend fun getTimetableXml(@Path("fileNumber") fileNumber: Int): ResponseBody
}