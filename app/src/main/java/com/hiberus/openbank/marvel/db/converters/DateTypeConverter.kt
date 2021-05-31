package com.hiberus.openbank.marvel.db.converters

import androidx.room.TypeConverter
import java.util.*

class DateTypeConverter {

    @TypeConverter
    fun fromTimesTamp(value: Long): Date? {
        return value?.let { (Date(it)) }
    }
    @TypeConverter
    fun dateToTimestamp(date:Date?):Long?{
        return date?.time?.toLong()
    }
}