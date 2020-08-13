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
        val refuelingId: Long,
        @SerializedName("provider")
        val provider: String,
        @SerializedName("fuelType")
        val fuelType: String,
        @SerializedName("fuelAmount")
        val fuelAmount: Float,
        @SerializedName("cost")
        val cost: Float,
        @SerializedName("location")
        @TypeConverters(DataConverter::class)
        val location: Location
) {
    constructor() : this(0, "", "", 0F, 0F, Location())
}