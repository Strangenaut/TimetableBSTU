package com.strangenaut.timetablebstu.feature_timetable.data.util

import android.app.Application
import com.chaquo.python.PyException
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

class XmlConverter(
    val app: Application
) {

    fun convertXmlToJson(xmlString: String): String {
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(app))
        }

        val pyObj: PyObject? = try {
            Python.getInstance()
                .getModule("convert_xml_to_json")
                .callAttr("convert_xml_to_json", xmlString)
        } catch (e: PyException) {
            println("Error parsing XML to JSON: ${e.message}")
            throw PyException(e.message)
        }
        return pyObj.toString()
    }
}