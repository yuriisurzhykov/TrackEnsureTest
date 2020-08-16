package com.yuriysurzhikov.trackensuretest.model.roomRepository.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yuriysurzhikov.trackensuretest.model.entities.Place

@Dao
interface PlaceDao {

    @Query("SELECT * FROM place WHERE address =:address LIMIT 1")
    fun getPlaceByAddress(address: String): Place

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlace(place: Place)

    @Query("SELECT * FROM place")
    fun getAllPlaces(): List<Place>
    @Query("SELECT * FROM place")
    fun getAll(): LiveData<List<Place>>
    @Transaction
    fun deletePlace(placeName: String) {
        delete(getPlaceByAddress(placeName))
    }
    @Delete
    fun delete(place: Place)
}