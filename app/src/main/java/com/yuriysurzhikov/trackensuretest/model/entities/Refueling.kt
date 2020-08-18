package com.yuriysurzhikov.trackensuretest.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class Refueling (
        @PrimaryKey
        @SerializedName("refuelingId")
        var refuelingId: String = UUID.randomUUID().toString(),
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
    constructor() : this(UUID.randomUUID().toString(), "", 0, 0F, "","")
}