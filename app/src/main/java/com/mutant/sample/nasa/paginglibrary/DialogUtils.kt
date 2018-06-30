package com.mutant.sample.nasa.paginglibrary

import android.app.DatePickerDialog
import android.content.Context
import android.widget.TextView
import java.util.*


object DialogUtils {

    fun createPickDateDialog(context: Context, textViewDate: TextView, minDate: Long): DatePickerDialog {
        val calendar = Calendar.getInstance()
        val splits = textViewDate.text.split("-")
        val initYear = if (splits.isNotEmpty()) splits[0].toInt() else calendar.get(Calendar.YEAR)
        val initMonth = if (splits.isNotEmpty() && splits.size > 1) splits[1].toInt() - 1 else calendar.get(Calendar.MONTH)
        val initDay = if (splits.isNotEmpty() && splits.size > 2) splits[2].toInt() else calendar.get(Calendar.DAY_OF_MONTH)
        val dialog = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { _, year, month, day ->
            textViewDate.text = context.getString(R.string.date_format, year, month + 1, day)
        }, initYear, initMonth, initDay)
        dialog.datePicker.minDate = minDate
        dialog.datePicker.maxDate = Calendar.getInstance().timeInMillis
        return dialog
    }
}