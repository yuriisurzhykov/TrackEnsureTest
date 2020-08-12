package com.yuriysurzhikov.trackensuretest.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Location(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val lat: Float,
        val lng: Float,
        val placeName: String
)