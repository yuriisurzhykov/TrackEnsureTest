package com.yuriysurzhikov.trackensuretest.model.firebaseRepository

import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.gson.Gson
import com.yuriysurzhikov.trackensuretest.config.App
import com.yuriysurzhikov.trackensuretest.model.entities.Place
import com.yuriysurzhikov.trackensuretest.model.entities.PlaceWithRefueling
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling
import com.yuriysurzhikov.trackensuretest.model.roomRepository.RoomDataProvider
import java.sql.Ref

object FirebaseDownloader {

    private const val TAG = "FirebaseDownloader"
    private const val CHANNEL_ID = "CHANNEL_ID"
    private const val PLACE_COLLECTION = "place"
    private const val FIELD_ADDRESS = "address"
    private const val FIELD_PROVIDER = "provider"
    private const val FIELD_LOCATION = "location"
    private const val REFUELING_COLLECTION = "refueling"
    private const val REFUELING_ID = "refuelingId"
    private const val REFUELING_FUEL_TYPE = "fuelType"
    private const val REFUELING_FUEL_AMOUNT = "fuelAmount"
    private const val REFUELING_TOTAL_COST = "fuel"
    private const val REFUELING_PLACE_ADDRESS = "addressCreator"
    private const val REFUELING_PLACE_PROVIDER = "addressCreator"

    private val remoteDB = FirebaseFirestore.getInstance().apply {
        firestoreSettings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build()
    }

    private val roomRepository = RoomDataProvider.getInstance()


    private var listOfPlaces = mutableListOf<Place>()
    private var listOfRefueling = mutableListOf<Refueling>()

    init {
        remoteDB
                .collection(PLACE_COLLECTION)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val place = document.toObject(Place::class.java)
                        Log.d(TAG, "downloadDataPlaces: $place")
                        roomRepository.addPlace(place)
                        remoteDB.collection(PLACE_COLLECTION)
                                .document(place.address)
                                .collection(REFUELING_COLLECTION)
                                .get()
                                .addOnSuccessListener {
                                    for (refueling in it){
                                        val ref = refueling.toObject(Refueling::class.java)
                                        Log.d(TAG, "init: $ref")
                                        roomRepository.addRefuelingNote(place, ref)
                                        //listOfRefueling.add(ref)
                                    }
                                }
                    }
                }
        /*Log.d(TAG, "init: listOfPlaces size ${listOfPlaces.size}")
        for (place in listOfPlaces) {

        }*/

    }

    fun downloadDataPlaces(): List<Place> {
        return listOfPlaces
    }
    fun downloadDataRefuelings(): List<Refueling> {
        return listOfRefueling
    }
}