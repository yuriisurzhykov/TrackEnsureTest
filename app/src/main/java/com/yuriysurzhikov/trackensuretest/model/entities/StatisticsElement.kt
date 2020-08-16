package com.yuriysurzhikov.trackensuretest.model.entities

import androidx.room.ColumnInfo

data class StatisticsElement(
        @ColumnInfo(name = "fuelType")
        var fuelType: String,
        @ColumnInfo(name = "SUM(fuelAmount)")
        var sumFuelAmount: Int,
        @ColumnInfo(name = "SUM(cost)")
        var sumCost: Float,
        @ColumnInfo(name = "addressCreator")
        var addressCreator: String,
        @ColumnInfo(name = "providerCreator")
        var providerCreator: String
)
