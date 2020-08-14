package com.yuriysurzhikov.trackensuretest.model.roomRepository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.yuriysurzhikov.trackensuretest.config.App;
import com.yuriysurzhikov.trackensuretest.model.MainRepositoryContract;
import com.yuriysurzhikov.trackensuretest.model.entities.Location;
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RoomDataProvider implements MainRepositoryContract {

    private Context context;
    private LocalDatabase database;
    private LiveData<List<Refueling>> gasStations;

    private RoomDataProvider(Context context) {
        this.context = context;
        database = App.getInstance().getDatabase();
        gasStations = database.stationsDao().getAll();
    }

    public static RoomDataProvider getInstance(Context context) {
        return new RoomDataProvider(context);
    }

    @NotNull
    @Override
    public LiveData<List<Refueling>> getAllRefuelings() {
        /*addRefuelingNote(new Refueling(
                0,
                "WOG",
                "Disel",
                120,
                1200,
                new Location(
                        12.145214,
                        45.412511,
                        "Автозаправка WOG"
                )
        ));
        addRefuelingNote(new Refueling(
                0,
                "WOG",
                "Disel",
                120,
                1200,
                new Location(
                        12.145214,
                        45.412511,
                        "Автозаправка WOG"
                )
        ));
        addRefuelingNote(new Refueling(
                0,
                "WOG",
                "Disel",
                120,
                1200,
                new Location(
                        12.145214,
                        45.412511,
                        "Автозаправка WOG"
                )
        ));
        addRefuelingNote(new Refueling(
                0,
                "WOG",
                "Disel",
                120,
                1200,
                new Location(
                        12.145214,
                        45.412511,
                        "Автозаправка WOG"
                )
        ));
        addRefuelingNote(new Refueling(
                0,
                "WOG",
                "Disel",
                120,
                1200,
                new Location(
                        12.145214,
                        45.412511,
                        "Автозаправка WOG"
                )
        ));*/
        return gasStations;
    }

    @Override
    public void addRefuelingNote(@NotNull Refueling station) {
        new InsertAsyncTask(database).execute(station);
    }

    @Override
    public void deleteRefuelingNote(@NotNull Refueling station) {
        new DeleteAsyncTask(database).execute(station);
    }

    @Override
    public void updateRefuelingNote(@NotNull Refueling station) {
        new UpdateAsyncTask(database).execute(station);
    }

    private static class DeleteAsyncTask extends AsyncTask<Refueling, Void, Void> {
        private LocalDatabase db;
        DeleteAsyncTask(LocalDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(final Refueling... gasStations) {
            db.stationsDao().deleteStation(gasStations[0]);
            return null;
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Refueling, Void, Void> {
        private LocalDatabase db;
        InsertAsyncTask(LocalDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(final Refueling... gasStations) {
            db.stationsDao().insertStation(gasStations[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Refueling, Void, Void> {
        private LocalDatabase db;
        UpdateAsyncTask(LocalDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(final Refueling... gasStations) {
            db.stationsDao().updateStation(gasStations[0]);
            return null;
        }
    }
}
