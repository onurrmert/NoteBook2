package com.example.notebook2.Util

import android.content.Context
import java.util.*

class MyDate {
    companion object{
         fun myDate(context: Context) : String{
            val date = Date().time
            val dateFormat = android.text.format.DateFormat.getDateFormat(context)
            return dateFormat.format(date)
        }
    }
}