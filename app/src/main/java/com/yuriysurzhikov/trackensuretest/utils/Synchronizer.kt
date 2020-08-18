package com.yuriysurzhikov.trackensuretest.utils

import androidx.work.*
import com.yuriysurzhikov.trackensuretest.config.App
import com.yuriysurzhikov.trackensuretest.model.services.DownloadDataFromFirestoreWorker
import com.yuriysurzhikov.trackensuretest.model.services.SyncRoomWithFirestoreWorker

class Synchronizer {
    fun sync() {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val workRequestPlace = OneTimeWorkRequest.Builder(DownloadDataFromFirestoreWorker::class.java)
                .setConstraints(constraints)
                .build()
        val workRequestRefueling = OneTimeWorkRequest.Builder(SyncRoomWithFirestoreWorker::class.java)
                .setConstraints(constraints)
                .build()

        val workManager = WorkManager.getInstance(App.getInstance().applicationContext)
        workManager
                .beginWith(workRequestPlace)
                .then(workRequestRefueling)
                .enqueue()
    }
}