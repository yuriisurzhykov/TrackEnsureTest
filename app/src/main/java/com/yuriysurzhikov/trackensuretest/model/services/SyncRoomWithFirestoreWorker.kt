package com.yuriysurzhikov.trackensuretest.model.services

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.yuriysurzhikov.trackensuretest.model.entities.Place
import com.yuriysurzhikov.trackensuretest.model.firebaseRepository.FirebaseDownloader
import com.yuriysurzhikov.trackensuretest.model.roomRepository.RoomDataProvider

class SyncRoomWithFirestoreWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    val roomRepository = RoomDataProvider.getInstance(context)
    override fun doWork(): Result {
        val listOfRefueling = FirebaseDownloader.downloadDataRefuelings()
        val listOfPlaces = FirebaseDownloader.downloadDataPlaces()
        println("SyncRoomWithFirestoreWorker/ doWork: listPlaces size" + listOfPlaces.size)
        for (place in listOfPlaces) {
            roomRepository.addPlace(place)
        }
        for(refueling in listOfRefueling)
            roomRepository.addRefuelingNote(listOfPlaces[0], refueling)
        //Result.success(Data.Builder().putString("place", Gson().toJson(listOfPlaces[0])).build())
        return Result.success()
    }
}