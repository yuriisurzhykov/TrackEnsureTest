package com.yuriysurzhikov.trackensuretest.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GasStation (
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val provider: String,
        val fuelType: String,
        val fuelAmount: Float,
        val cost: Float
)