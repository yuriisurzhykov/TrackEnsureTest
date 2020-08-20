package com.yuriysurzhikov.trackensuretest.model;

import androidx.lifecycle.LiveData;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.google.gson.Gson;
import com.yuriysurzhikov.trackensuretest.config.App;
import com.yuriysurzhikov.trackensuretest.model.entities.Place;
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling;
import com.yuriysurzhikov.trackensuretest.model.entities.StatisticsLive;
import com.yuriysurzhikov.trackensuretest.model.firebaseRepository.FirestoreRepository;
import com.yuriysurzhikov.trackensuretest.model.roomRepository.RoomDataProvider;
import com.yuriysurzhikov.trackensuretest.model.services.DeleteRefuelingWorker;
import com.yuriysurzhikov.trackensuretest.model.services.InsertRefuelingWorker;
import com.yuriysurzhikov.trackensuretest.model.services.UpdateRefuelingWorker;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainRepository {

    private RoomDataProvider roomDataProvider;
    private FirestoreRepository firestoreRepository;
    private static MainRepository instance = null;

    private MainRepository() {
        roomDataProvider = RoomDataProvider.getInstance();
        firestoreRepository = new FirestoreRepository();
    }

    public static MainRepository getInstance() {
        if(instance == null)
            instance = new MainRepository();
        return instance;
    }

    @NotNull
    public LiveData<List<Refueling>> getAllRefuelingRecords() {
        return roomDataProvider.getAllRefuelingRecords();
    }

    public Place getPlaceByAddress(String id) {
        return roomDataProvider.getPlaceByAddress(id);
    }

    public void addRefuelingNote(@NotNull Place place, @NotNull Refueling refueling) {
        roomDataProvider.addRefuelingNote(place, refueling);
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        Data data = new Data.Builder()
                .putString("place", new Gson().toJson(place))
                .putString("refueling", new Gson().toJson(refueling))
                .build();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(InsertRefuelingWorker.class)
                .setConstraints(constraints)
                .setInputData(data)
                .build();
        WorkManager workManager = WorkManager.getInstance(App.getInstance().getApplicationContext());
        workManager.beginWith(workRequest)
                .enqueue();
        //firestoreRepository.addRefuelingNote(place, refueling);
    }


    public void deleteRefuelingNote(@NotNull Refueling refueling) {
        roomDataProvider.deleteRefuelingNote(refueling);
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        Data data = new Data.Builder()
                .putString("refueling", new Gson().toJson(refueling))
                .build();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(DeleteRefuelingWorker.class)
                .setConstraints(constraints)
                .setInputData(data)
                .build();
        WorkManager workManager = WorkManager.getInstance(App.getInstance().getApplicationContext());
        workManager.beginWith(workRequest)
                .enqueue();
    }


    public void updateRefuelingNote(@NotNull Refueling stationOld, Refueling stationNew, Place place) {
        roomDataProvider.updateRefuelingNote(stationOld, stationNew, place);
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        Data data = new Data.Builder()
                .putString("place", new Gson().toJson(place))
                .putString("refuelingOld", new Gson().toJson(stationOld))
                .putString("refuelingNew", new Gson().toJson(stationNew))
                .build();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(UpdateRefuelingWorker.class)
                .setConstraints(constraints)
                .setInputData(data)
                .build();
        WorkManager workManager = WorkManager.getInstance(App.getInstance().getApplicationContext());
        workManager.beginWith(workRequest)
                .enqueue();
    }

    public LiveData<List<Place>> getAllPlaces() {
        return roomDataProvider.getAllPlaces();
    }

    @NotNull
    public LiveData<List<StatisticsLive>> highlightStatistics() {
        return roomDataProvider.highlightStatistics();
    }
}
