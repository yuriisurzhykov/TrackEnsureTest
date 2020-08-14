package com.yuriysurzhikov.trackensuretest.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.yuriysurzhikov.trackensuretest.utils.DataConverter

@Entity
@TypeConverters(DataConverter::class)
data class Refueling (
        @PrimaryKey(autoGenerate = true)
        @SerializedName("refuelingId")
        var refuelingId: Long,
        @SerializedName("provider")
        var provider: String,
        @SerializedName("fuelType")
        var fuelType: String,
        @SerializedName("fuelAmount")
        var fuelAmount: Float,
        @SerializedName("cost")
        var cost: Float,
        @SerializedName("location")
        @TypeConverters(DataConverter::class)
        var location: Location
) {
    constructor() : this(0, "", "", 0F, 0F, Location())
}