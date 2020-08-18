package com.yuriysurzhikov.trackensuretest.model.services

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.yuriysurzhikov.trackensuretest.model.firebaseRepository.FirebaseDownloader
import com.yuriysurzhikov.trackensuretest.model.firebaseRepository.FirestoreRepository
import com.yuriysurzhikov.trackensuretest.model.roomRepository.RoomDataProvider

class DownloadDataFromFirestoreWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    private var roomRepository = RoomDataProvider.getInstance(context)

    override fun doWork(): Result {
        val downloadDataPlaces = FirebaseDownloader.downloadDataPlaces()
        return Result.success()
    }
}