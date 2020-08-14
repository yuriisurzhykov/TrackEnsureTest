package com.yuriysurzhikov.trackensuretest.model.entities

import com.google.gson.annotations.SerializedName

data class Location (
        @SerializedName("lat")
        var lat: Double,
        @SerializedName("lng")
        var lng: Double,
        @SerializedName("placeName")
        var placeName: String
) {
    constructor() : this(0.0, 0.0, "")
}