package com.yuriysurzhikov.trackensuretest.model

import androidx.lifecycle.LiveData
import com.yuriysurzhikov.trackensuretest.model.entities.Place
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling
import com.yuriysurzhikov.trackensuretest.model.entities.StatisticsLive

interface MainRepositoryContract {
    fun getAllRefuelingRecords(): LiveData<MutableList<Refueling>>
    fun addRefuelingNote(place: Place, station: Refueling)
    fun deleteRefuelingNote(station: Refueling)
    fun updateRefuelingNote(station: Refueling, place: Place)
    fun highlightStatistics(): LiveData<MutableList<StatisticsLive>>
}