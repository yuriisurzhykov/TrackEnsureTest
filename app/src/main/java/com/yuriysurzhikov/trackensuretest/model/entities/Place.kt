package com.yuriysurzhikov.trackensuretest.model.entities


import androidx.room.*
import com.google.maps.model.LatLng
import com.google.gson.annotations.SerializedName
import com.yuriysurzhikov.trackensuretest.utils.LocationConverter

@Entity(indices = [Index(value = ["address"], unique = true)],
        primaryKeys = ["address"])
@TypeConverters(LocationConverter::class)
data class Place(
        @SerializedName("provider")
        var provider: String,
        @SerializedName("address")
        @ColumnInfo(name = "address")
        var address: String,
        @TypeConverters(LocationConverter::class)
        @SerializedName("location")
        @ColumnInfo(name = "location")
        var location: LatLng
) {
    constructor(): this( "", "", LatLng())
}