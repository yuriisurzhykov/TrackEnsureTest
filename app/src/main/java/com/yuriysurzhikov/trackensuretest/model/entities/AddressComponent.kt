package com.yuriysurzhikov.trackensuretest.model.entities

import com.google.gson.annotations.SerializedName

data class AddressComponent(
        @SerializedName("address_components")
        var addressComponent: List<ResultClass>
)