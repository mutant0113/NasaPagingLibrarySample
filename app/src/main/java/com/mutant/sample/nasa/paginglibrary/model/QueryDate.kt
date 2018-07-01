package com.mutant.sample.nasa.paginglibrary.model

import com.mutant.sample.nasa.paginglibrary.DateUtils
import java.util.*


data class QueryDate(private var startDate: Date, private val endDate: Date) {

    private val requestedEndCalendar: Calendar = Calendar.getInstance()

    init {
        requestedEndCalendar.time = startDate
        increaseMonth()
    }

    fun getStartDateStr(): String {
        return DateUtils.dateToDateStr(startDate)
    }

    /**
     * This is for local cache to query all apod data
     */
    fun getEndDateStr() : String {
        return DateUtils.dateToDateStr(endDate)
    }

    /**
     * This is for service to query apod data separating by one month
     */
    fun getLastRequestedEndDateStr(): String {
        return DateUtils.dateToDateStr(requestedEndCalendar.time)
    }

    fun increaseMonth() {
        startDate = requestedEndCalendar.time
        requestedEndCalendar.add(Calendar.MONTH, 1)
        if (requestedEndCalendar.time.time > endDate.time) {
            requestedEndCalendar.time = endDate
        }
    }
}