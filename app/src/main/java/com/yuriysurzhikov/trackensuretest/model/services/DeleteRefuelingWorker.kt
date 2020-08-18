package com.yuriysurzhikov.trackensuretest.model.services

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling
import com.yuriysurzhikov.trackensuretest.model.firebaseRepository.FirestoreRepository

class DeleteRefuelingWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        //val place = Gson().fromJson(inputData.getString("place"), Place::class.java)
        val refueling = Gson().fromJson(inputData.getString("refueling"), Refueling::class.java)
        FirestoreRepository().deleteRefuelingNote(refueling)
        return Result.success()
    }
}