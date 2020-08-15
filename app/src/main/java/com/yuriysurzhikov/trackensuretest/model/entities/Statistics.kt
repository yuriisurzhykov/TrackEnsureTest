package com.yuriysurzhikov.trackensuretest.model.entities

import androidx.room.Entity

@Entity(tableName = "place")
data class Statistics (
        var provider: String,
        var address: String,
        var elements: List<StatisticsElement>
)