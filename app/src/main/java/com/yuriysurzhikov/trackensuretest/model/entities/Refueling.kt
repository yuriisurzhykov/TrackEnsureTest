package com.yuriysurzhikov.trackensuretest.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Refueling (
        @PrimaryKey(autoGenerate = true)
        @SerializedName("refuelingId")
        var refuelingId: Long,
        @SerializedName("fuelType")
        var fuelType: String,
        @SerializedName("fuelAmount")
        var fuelAmount: Int,
        @SerializedName("cost")
        var cost: Float,
        @SerializedName("addressCreator")
        @ForeignKey(entity = Place::class, parentColumns = ["provider", "address"], childColumns = ["providerCreator", "addressCreator"])
        var addressCreator: String,
        @SerializedName("providerCreator")
        var providerCreator: String
) {
    constructor() : this(0, "", 0, 0F, "","")
}