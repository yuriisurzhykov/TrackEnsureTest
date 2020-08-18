package com.yuriysurzhikov.trackensuretest.utils

import android.text.TextUtils
import com.google.maps.model.LatLng
import androidx.room.TypeConverter
import com.google.gson.Gson

class LocationConverter {
    @TypeConverter
    fun stringToLocation(string: String): LatLng? {
        return when (TextUtils.isEmpty(string)) {
            true -> null
            else -> Gson().fromJson(string, LatLng::class.java)
        }
    }

    @TypeConverter
    fun locationToString(outboxItem: LatLng): String {
        return Gson().toJson(outboxItem)
    }
}