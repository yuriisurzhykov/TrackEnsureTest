package com.yuriysurzhikov.trackensuretest.model

import androidx.lifecycle.LiveData
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling
import com.yuriysurzhikov.trackensuretest.model.entities.Statistics

interface MainRepositoryContract {
    fun getAllRefuelings(): LiveData<MutableList<Refueling>>
    fun addRefuelingNote(station: Refueling)
    fun deleteRefuelingNote(station: Refueling)
    fun updateRefuelingNote(station: Refueling)
    fun highlightStatistics(): List<Statistics>
}