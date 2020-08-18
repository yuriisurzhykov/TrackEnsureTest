package com.yuriysurzhikov.trackensuretest.model.firebaseRepository


import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.SetOptions
import com.google.gson.Gson
import com.yuriysurzhikov.trackensuretest.R
import com.yuriysurzhikov.trackensuretest.config.App
import com.yuriysurzhikov.trackensuretest.model.MainRepositoryContract
import com.yuriysurzhikov.trackensuretest.model.entities.*
import com.yuriysurzhikov.trackensuretest.view.activities.MainActivity

open class FirestoreRepository : MainRepositoryContract {

    companion object {
        private const val TAG = "FirestoreRepository"
        private const val CHANNEL_ID = "CHANNEL_ID"
        private const val PLACE_COLLECTION = "place"
        private const val FIELD_ADDRESS = "address"
        private const val FIELD_PROVIDER = "provider"
        private const val FIELD_LOCATION = "location"
        private const val REFUELING_COLLECTION = "refueling"
        private const val REFUELING_ID = "refuelingId"
        private const val REFUELING_FUEL_TYPE = "fuelType"
        private const val REFUELING_FUEL_AMOUNT = "fuelAmount"
        private const val REFUELING_TOTAL_COST = "cost"
        private const val REFUELING_PLACE_ADDRESS = "addressCreator"
        private const val REFUELING_PLACE_PROVIDER = "providerCreator"
        private var notificationId = 0
    }

    private val remoteDB = FirebaseFirestore.getInstance().apply {
        firestoreSettings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build()
    }

    private fun createPlaceIfNotExists(place: Place) {
        val placeMap = hashMapOf(
                FIELD_ADDRESS to place.address,
                FIELD_PROVIDER to place.provider,
                FIELD_LOCATION to place.location
        )
        remoteDB.collection(PLACE_COLLECTION)
                .document(place.address)
                .set(placeMap, SetOptions.merge())
                .addOnSuccessListener {
                    //showNotification("Place imported", "Data have been successfully sent to Cloud Firestore! Keep calm, and enjoy life ;)")
                }
                .addOnFailureListener {
                    //showNotification("Place don't imported", "Ann error occurred while sending data to Cloud Firestore! Please, don't panic!")
                }
    }

    private fun deletePlaceIfHaveNotChildren(placeAddress: String) {
        remoteDB
                .collection(PLACE_COLLECTION)
                .document(placeAddress)
                .collection(REFUELING_COLLECTION)
                .get()
                .addOnCompleteListener {
                    if (it.result?.documents?.size == 0) {
                        remoteDB
                                .collection(PLACE_COLLECTION)
                                .document(placeAddress)
                                .delete()
                    }
                }
    }

    override fun getAllRefuelingRecords(): LiveData<MutableList<Refueling>> {
        return MediatorLiveData()
    }

    override fun addRefuelingNote(place: Place, station: Refueling) {

        createPlaceIfNotExists(place)

        val refuelingMap = hashMapOf(
                REFUELING_ID to station.refuelingId,
                REFUELING_FUEL_AMOUNT to station.fuelAmount,
                REFUELING_FUEL_TYPE to station.fuelType,
                REFUELING_TOTAL_COST to station.cost,
                REFUELING_PLACE_ADDRESS to station.addressCreator,
                REFUELING_PLACE_PROVIDER to station.providerCreator
        )
        remoteDB.collection(PLACE_COLLECTION)
                .document(place.address)
                .collection(REFUELING_COLLECTION)
                .document(station.refuelingId)
                .set(refuelingMap, SetOptions.merge())
                .addOnSuccessListener {
                    showNotification("Refueling imported", "Data have been successfully sent to Cloud Firestore! Keep calm, and enjoy life ;)")
                }
                .addOnFailureListener {
                    showNotification("Refueling don't imported", "Ann error occurred while sending data to Cloud Firestore! Please, don't panic!")
                }
    }

    override fun deleteRefuelingNote(station: Refueling) {
        remoteDB.collection(PLACE_COLLECTION)
                .document(station.addressCreator)
                .collection(REFUELING_COLLECTION)
                .document(station.refuelingId)
                .delete()
                .addOnSuccessListener {
                    showNotification("Refueling was delete!", "Refueling record was successfully deleted from server!")
                }
                .addOnFailureListener {
                    showNotification("Refueling was not delete!", "Refueling record cannot be deleted from server!")
                }
        deletePlaceIfHaveNotChildren(station.addressCreator)
    }

    override fun updateRefuelingNote(stationOld: Refueling, stationNew: Refueling, place: Place) {
        deleteRefuelingNote(stationOld)
        createPlaceIfNotExists(place)
        Log.d(TAG, "updateRefuelingNote: " + stationNew.addressCreator)
        val refuelingMap = hashMapOf(
                REFUELING_ID to stationNew.refuelingId,
                REFUELING_FUEL_AMOUNT to stationNew.fuelAmount,
                REFUELING_FUEL_TYPE to stationNew.fuelType,
                REFUELING_TOTAL_COST to stationNew.cost,
                REFUELING_PLACE_ADDRESS to stationNew.addressCreator,
                REFUELING_PLACE_PROVIDER to stationNew.providerCreator
        )
        remoteDB.collection(PLACE_COLLECTION)
                .document(place.address)
                .collection(REFUELING_COLLECTION)
                .document(stationNew.refuelingId)
                .set(refuelingMap, SetOptions.merge())
                .addOnSuccessListener {
                    showNotification("Refueling imported", "Data have been successfully sent to Cloud Firestore! Keep calm, and enjoy life ;)")
                }
                .addOnFailureListener {
                    showNotification("Refueling have not been update!", "Ann error occurred while sending data to Cloud Firestore! Please, don't panic!")
                }
    }

    override fun highlightStatistics(): LiveData<MutableList<StatisticsLive>> {
        return MediatorLiveData()
    }

    private fun showNotification(title: String, text: String) {
        val intent = Intent(App.getInstance().applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(App.getInstance().applicationContext, 0, intent, 0)
        val builder = NotificationCompat.Builder(App.getInstance().applicationContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_fuel_station)
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(NotificationCompat.BigTextStyle()
                        .bigText(text))
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
        with(NotificationManagerCompat.from(App.getInstance().applicationContext)) {
            notify(++notificationId, builder.build())
        }
    }
}