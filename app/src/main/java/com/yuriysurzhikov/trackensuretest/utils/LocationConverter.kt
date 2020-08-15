package com.yuriysurzhikov.trackensuretest.utils

import android.text.TextUtils
import androidx.room.TypeConverter
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.yuriysurzhikov.trackensuretest.model.entities.Location
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling

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