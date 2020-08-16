package com.yuriysurzhikov.trackensuretest.model.entities

import androidx.lifecycle.LiveData
import androidx.room.Entity

data class StatisticsLive (
        var provider: String,
        var address: String,
        var elements: LiveData<List<StatisticsElement>>
)