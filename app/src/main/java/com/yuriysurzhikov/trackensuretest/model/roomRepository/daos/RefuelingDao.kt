package com.yuriysurzhikov.trackensuretest.model.roomRepository.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling

@Dao
interface RefuelingDao {
    @Query("SELECT * FROM Refueling")
    fun getAll(): LiveData<List<Refueling>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRefuelingRecord(station: Refueling)
    @Delete
    fun deleteRefuelingRecord(station: Refueling)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateRefuelingRecord(station: Refueling)
}