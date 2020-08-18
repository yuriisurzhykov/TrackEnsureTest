package com.yuriysurzhikov.trackensuretest.model.services

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.yuriysurzhikov.trackensuretest.model.entities.Place
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling
import com.yuriysurzhikov.trackensuretest.model.firebaseRepository.FirestoreRepository

class UpdateRefuelingWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val refuelingOld = Gson().fromJson(inputData.getString("refuelingOld"), Refueling::class.java)
        val refuelingNew = Gson().fromJson(inputData.getString("refuelingNew"), Refueling::class.java)
        val place = Gson().fromJson(inputData.getString("place"), Place::class.java)
        FirestoreRepository().updateRefuelingNote(refuelingOld, refuelingNew, place)
        return Result.success()
    }
}