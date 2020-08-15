package com.yuriysurzhikov.trackensuretest.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Refueling (
        @PrimaryKey(autoGenerate = true)
        @SerializedName("refuelingId")
        var refuelingId: Long,
        @SerializedName("providerName")
        var providerName: String,
        @SerializedName("fuelType")
        var fuelType: String,
        @SerializedName("fuelAmount")
        var fuelAmount: Int,
        @SerializedName("cost")
        var cost: Float,
        @SerializedName("placeId")
        var placeCreatorId: Long
) {
    constructor() : this(0, "", "", 0, 0F, 0)
}