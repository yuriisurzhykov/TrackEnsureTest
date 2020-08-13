package com.yuriysurzhikov.trackensuretest.model.entities

import com.google.gson.annotations.SerializedName

data class Location (
        @SerializedName("locationId")
        val locationId: Long,
        @SerializedName("lat")
        val lat: Double,
        @SerializedName("lng")
        val lng: Double,
        @SerializedName("placeName")
        val placeName: String
)