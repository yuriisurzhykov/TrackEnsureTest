package com.yuriysurzhikov.trackensuretest.model.entities

import android.provider.ContactsContract
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName
import com.yuriysurzhikov.trackensuretest.utils.LocationConverter

@Entity
@TypeConverters(LocationConverter::class)
data class Place(
        @PrimaryKey(autoGenerate = true)
        @SerializedName("id")
        var placeId: Long,
        @SerializedName("provider")
        var provider: String,
        @SerializedName("address")
        var address: String,
        @TypeConverters(LocationConverter::class)
        @SerializedName("location")
        var location: LatLng
) {
    constructor(): this(0, "", "", LatLng(0.0, 0.0))
}