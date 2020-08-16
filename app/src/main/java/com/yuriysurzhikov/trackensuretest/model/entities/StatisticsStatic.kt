package com.yuriysurzhikov.trackensuretest.model.entities

import androidx.lifecycle.LiveData

data class StatisticsStatic(
        var provider: String,
        var address: String,
        var elements: List<StatisticsElement>
)