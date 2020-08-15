package com.yuriysurzhikov.trackensuretest.model.roomRepository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.yuriysurzhikov.trackensuretest.config.App;
import com.yuriysurzhikov.trackensuretest.model.MainRepositoryContract;
import com.yuriysurzhikov.trackensuretest.model.entities.Place;
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling;
import com.yuriysurzhikov.trackensuretest.model.entities.Statistics;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class RoomDataProvider implements MainRepositoryContract {

    private Context context;
    private LocalDatabase database;
    private LiveData<List<Refueling>> gasStations;

    private RoomDataProvider(Context context) {
        this.context = context;
        database = App.getInstance().getDatabase();
        gasStations = database.refuelingDao().getAll();
    }

    public static RoomDataProvider getInstance(Context context) {
        return new RoomDataProvider(context);
    }

    @NotNull
    @Override
    public LiveData<List<Refueling>> getAllRefuelingRecords() {
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
    public void addRefuelingNote(@NotNull Place place, Refueling station) {
        new InsertPlaceAsyncTask(database).execute(place);
        try {
            station.setPlaceCreatorId(getPlaceId(place.getAddress()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new InsertRefuelingAsyncTask(database).execute(station);
    }

    @Override
    public void deleteRefuelingNote(@NotNull Refueling station) {
        new DeleteAsyncTask(database).execute(station);
    }

    @Override
    public void updateRefuelingNote(@NotNull Refueling station) {
        new UpdateAsyncTask(database).execute(station);
    }

    private Long getPlaceId(String address) throws ExecutionException, InterruptedException {
        return new GetPlaceIdTask(database).execute(address).get();
    }

    public Place getPlaceById(Long id) {
        try {
            return new GetPlaceTask(database).execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @NotNull
    @Override
    public List<Statistics> highlightStatistics() {
        return null;
    }

    private static class GetPlaceTask extends AsyncTask<Long, Void, Place> {
        private LocalDatabase db;

        public GetPlaceTask(LocalDatabase database) {
            db = database;
        }

        @Override
        protected Place doInBackground(Long... strings) {
            return db.placeDao().getPlaceById(strings[0]);
        }
    }

    private static class GetPlaceIdTask extends AsyncTask<String, Void, Long> {
        private LocalDatabase db;

        public GetPlaceIdTask(LocalDatabase database) {
            db = database;
        }

        @Override
        protected Long doInBackground(String... strings) {
            return db.placeDao().getPlaceByAddress(strings[0]).getPlaceId();
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Refueling, Void, Void> {
        private LocalDatabase db;
        DeleteAsyncTask(LocalDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(final Refueling... gasStations) {
            db.refuelingDao().deleteRefuelingRecord(gasStations[0]);
            return null;
        }
    }

    private static class InsertRefuelingAsyncTask extends AsyncTask<Refueling, Void, Void> {
        private LocalDatabase db;
        public InsertRefuelingAsyncTask(LocalDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(Refueling... refuelings) {
            db.refuelingDao().insertRefuelingRecord(refuelings[0]);
            return null;
        }
    }

    private static class InsertPlaceAsyncTask extends AsyncTask<Place, Void, Void> {
        private LocalDatabase db;
        private Refueling refueling;
        InsertPlaceAsyncTask(LocalDatabase db) {
            this.db = db;
            this.refueling = refueling;
        }

        @Override
        protected Void doInBackground(final Place... places) {
            db.placeDao().addPlace(places[0]);
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
            db.refuelingDao().updateRefuelingRecord(gasStations[0]);
            return null;
        }
    }
}
