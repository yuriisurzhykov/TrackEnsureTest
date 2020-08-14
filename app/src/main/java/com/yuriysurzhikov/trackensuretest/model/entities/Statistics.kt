package com.yuriysurzhikov.trackensuretest.model.entities

data class Statistics (
        var provider: String,
        var address: String,
        var elements: List<StatisticsElement>
)