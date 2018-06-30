package com.mutant.sample.nasa.paginglibrary

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun dateToMillisecs(dateStr: String): Long {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN)
        val date = dateFormat.parse(dateStr)
        return date.time
    }
}