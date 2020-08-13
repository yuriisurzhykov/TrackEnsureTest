package com.yuriysurzhikov.trackensuretest.model

import androidx.lifecycle.LiveData
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling

interface MainRepositoryContract {
    fun getAllStations(): LiveData<MutableList<Refueling>>
    fun addRefuelingNote(station: Refueling)
    fun deleteRefuelingNote(station: Refueling)
    fun updateRefuelingNote(station: Refueling)
}