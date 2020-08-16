package com.yuriysurzhikov.trackensuretest.model.entities

import androidx.room.Embedded
import androidx.room.Relation

data class PlaceWithRefueling(
        @Embedded
        var place: Place,
        @Relation(
                parentColumn = "address",
                entityColumn = "addressCreator",
                entity = Refueling::class
        )
        var refuelings: List<Refueling>
) {
    constructor(): this(Place(), emptyList())
}