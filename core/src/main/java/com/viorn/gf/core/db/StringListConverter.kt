package com.viorn.gf.core.db

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.lang.reflect.Type

class StringListConverter {
    private val gson = Gson()
    private var listOfString: Type = object : TypeToken<List<String>>() {}.getType()

    @TypeConverter
    fun stringToList(value: String?): List<String> {
        if (value==null) return emptyList();
        return gson.fromJson(value, listOfString)
    }

    @TypeConverter
    fun listToString(list: List<String>): String {
        return gson.toJson(list)
    }
}