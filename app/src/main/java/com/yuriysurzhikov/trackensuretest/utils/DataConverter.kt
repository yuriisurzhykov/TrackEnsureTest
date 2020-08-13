package com.yuriysurzhikov.trackensuretest.utils

import android.text.TextUtils
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.yuriysurzhikov.trackensuretest.model.entities.Location
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling

class DataConverter {
    @TypeConverter
    fun stringToLocation(string: String): Location? {
        if (TextUtils.isEmpty(string))
            return null
        return Gson().fromJson(string, Location::class.java)
    }

    @TypeConverter
    fun locationToString(outboxItem: Location): String {
        return Gson().toJson(outboxItem)
    }
}