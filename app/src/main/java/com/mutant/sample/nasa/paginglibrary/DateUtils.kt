package com.mutant.sample.nasa.paginglibrary

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN)

    fun dateStrToMillisecs(dateStr: String): Long {
        val date = dateFormat.parse(dateStr)
        return date.time
    }

    fun dateToDateStr(date: Date) : String {
        return dateFormat.format(date).toString()
    }
}