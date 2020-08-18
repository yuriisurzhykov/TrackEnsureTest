package com.yuriysurzhikov.trackensuretest.config;

import android.app.Application;
import androidx.room.Room;
import com.yuriysurzhikov.trackensuretest.model.roomRepository.LocalDatabase;
import com.yuriysurzhikov.trackensuretest.utils.Synchronizer;

public class App extends Application {

    private static App instance;
    private LocalDatabase database;
    private static final String DBName = "localDB";

    public static App getInstance() {
        if (instance == null)
            instance = new App();
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(getApplicationContext(), LocalDatabase.class, DBName)
                .build();
    }

    public LocalDatabase getDatabase() {
        if (database == null) {
            synchronized (LocalDatabase.class) {
                if (database == null) {
                    database = Room.databaseBuilder(getApplicationContext(), LocalDatabase.class, DBName)
                            .build();
                }
            }
        }
        return database;
    }
}
