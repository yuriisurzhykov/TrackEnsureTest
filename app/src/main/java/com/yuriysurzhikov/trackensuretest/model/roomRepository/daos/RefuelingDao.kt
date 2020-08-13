package com.yuriysurzhikov.trackensuretest.model.roomRepository.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling

@Dao
interface RefuelingDao {
    @Query("SELECT * FROM Refueling")
    fun getAll(): LiveData<List<Refueling>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertStation(station: Refueling)
    @Delete
    fun deleteStation(station: Refueling)
    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateStation(station: Refueling)
}