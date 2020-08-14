package com.yuriysurzhikov.trackensuretest.model.entities

import com.google.gson.annotations.SerializedName

data class ResultClass(
        @SerializedName("long_name")
        var longName: String,
        @SerializedName("short_name")
        var shortName: String,
        @SerializedName("types")
        var types: List<String>
)