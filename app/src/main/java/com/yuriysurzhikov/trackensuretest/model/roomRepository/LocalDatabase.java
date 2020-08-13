package com.yuriysurzhikov.trackensuretest.model.roomRepository;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.yuriysurzhikov.trackensuretest.model.entities.Refueling;
import com.yuriysurzhikov.trackensuretest.model.roomRepository.daos.RefuelingDao;

@Database(entities = {Refueling.class}, version = 1)
public abstract class LocalDatabase extends RoomDatabase {
    public abstract RefuelingDao stationsDao();
}
