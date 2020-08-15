package com.yuriysurzhikov.trackensuretest.model;

import androidx.lifecycle.LiveData;

import com.yuriysurzhikov.trackensuretest.config.App;
import com.yuriysurzhikov.trackensuretest.model.entities.Place;
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling;
import com.yuriysurzhikov.trackensuretest.model.entities.Statistics;
import com.yuriysurzhikov.trackensuretest.model.roomRepository.RoomDataProvider;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainRepository implements MainRepositoryContract {

    private RoomDataProvider roomDataProvider;
    private static MainRepository instance = null;

    private MainRepository() {
        roomDataProvider = RoomDataProvider.getInstance(App.getInstance().getApplicationContext());
    }

    public static MainRepository getInstance() {
        if(instance == null)
            instance = new MainRepository();
        return instance;
    }

    @NotNull
    @Override
    public LiveData<List<Refueling>> getAllRefuelingRecords() {
        return roomDataProvider.getAllRefuelingRecords();
    }

    public Place getPlaceById(Long id) {
        return roomDataProvider.getPlaceById(id);
    }

    @Override
    public void addRefuelingNote(@NotNull Place place, @NotNull Refueling refueling) {
        roomDataProvider.addRefuelingNote(place, refueling);
    }

    @Override
    public void deleteRefuelingNote(@NotNull Refueling refueling) {
        roomDataProvider.deleteRefuelingNote(refueling);
    }

    @Override
    public void updateRefuelingNote(@NotNull Refueling station) {
        roomDataProvider.updateRefuelingNote(station);
    }

    @NotNull
    @Override
    public List<Statistics> highlightStatistics() {
        return null;
    }
}
