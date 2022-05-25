package com.breaktime.lab1.data

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun toDate(dateLong: Long) = Date(dateLong)

    @TypeConverter
    fun fromDate(date: Date) = date.time
}