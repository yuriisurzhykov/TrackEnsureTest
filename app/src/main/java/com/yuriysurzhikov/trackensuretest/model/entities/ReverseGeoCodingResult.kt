package com.yuriysurzhikov.trackensuretest.model.entities

import com.google.gson.annotations.SerializedName
import com.google.maps.model.Geometry

data class ReverseGeoCodingResult(
        @SerializedName("results")
        var results: List<AddressComponent>,
        @SerializedName("formatted_address")
        var formattedAddress: String
)