package com.yuriysurzhikov.trackensuretest.model.roomRepository.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuriysurzhikov.trackensuretest.model.entities.Place

@Dao
interface PlaceDao {

    @Query("SELECT * FROM place WHERE address =:address LIMIT 1")
    fun getPlaceByAddress(address: String): Place

    @Query("SELECT * FROM place WHERE placeId =:id LIMIT 1")
    fun getPlaceById(id: Long): Place

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlace(place: Place)
}