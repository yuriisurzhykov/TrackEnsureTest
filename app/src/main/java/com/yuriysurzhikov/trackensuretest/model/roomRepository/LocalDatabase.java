package com.yuriysurzhikov.trackensuretest.model.roomRepository;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.yuriysurzhikov.trackensuretest.model.entities.Place;
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling;
import com.yuriysurzhikov.trackensuretest.model.roomRepository.daos.PlaceDao;
import com.yuriysurzhikov.trackensuretest.model.roomRepository.daos.RefuelingDao;

@Database(entities = {Place.class, Refueling.class}, version = 2)
public abstract class LocalDatabase extends RoomDatabase {
    public abstract RefuelingDao refuelingDao();
    public abstract PlaceDao placeDao();
}
