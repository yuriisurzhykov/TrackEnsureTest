package com.yuriysurzhikov.trackensuretest.model.roomRepository.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling
import com.yuriysurzhikov.trackensuretest.model.entities.StatisticsElement

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
    @Transaction @Query("SELECT fuelType, SUM(fuelAmount), SUM(cost), addressCreator, providerCreator FROM Refueling GROUP BY fuelType, addressCreator ORDER BY addressCreator")
    fun getSortedRefuelingRecords(): LiveData<List<StatisticsElement>>
    @Query("SELECT fuelType, SUM(fuelAmount), SUM(cost), addressCreator, providerCreator FROM Refueling WHERE addressCreator =:address GROUP BY fuelType, addressCreator ORDER BY addressCreator")
    fun getStatisticsElementByAddress(address: String): LiveData<List<StatisticsElement>>

    @Query("SELECT * FROM REFUELING WHERE addressCreator =:address")
    fun getRefuelingByAddress(address: String): List<Refueling>
}