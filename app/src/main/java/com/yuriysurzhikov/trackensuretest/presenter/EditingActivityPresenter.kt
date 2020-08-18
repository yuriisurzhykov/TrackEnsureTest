package com.yuriysurzhikov.trackensuretest.presenter

import android.content.Context
import android.util.Log
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.yuriysurzhikov.trackensuretest.model.MainRepository
import com.yuriysurzhikov.trackensuretest.model.entities.Place
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling
import com.yuriysurzhikov.trackensuretest.model.gecoding.ReverseGeocoding
import com.yuriysurzhikov.trackensuretest.presenter.contracts.EditingActivityContract
import com.yuriysurzhikov.trackensuretest.utils.Observer

class EditingActivityPresenter(val view: EditingActivityContract.View, val context: Context) : EditingActivityContract.Presenter, Observer {

    private var modelRefuelOld = Refueling()
    private var modelRefuelNew = Refueling()
    private var modelPlace = Place()
    private val TAG = "EditingActivityPresent"

    override fun loadLocation(location: LatLng) {
        modelPlace.location = com.google.maps.model.LatLng(location.latitude, location.longitude)
        ReverseGeocoding(context, this, location)
    }

    override fun updateRefueling() {
        if(checkFields()) {
            Log.d(TAG, "updateRefueling: " + modelRefuelOld.addressCreator)
            MainRepository.getInstance().updateRefuelingNote(modelRefuelOld, modelRefuelNew, modelPlace)
            view.showMessage("Data has been successfully saved!")
            view.closeActivity()
        } else {
            view.showMessage("Not all fields are filled!")
        }
    }

    override fun changeProvider(name: String) {
        modelRefuelNew.providerCreator = name
        modelPlace.provider = name
    }

    override fun changeFuelType(type: String) {
        modelRefuelNew.fuelType = type
    }

    override fun changeAmount(amount: Int) {
        modelRefuelNew.fuelAmount = amount
    }

    override fun changeCost(cost: Float) {
        modelRefuelNew.cost = cost
    }

    override fun checkFields(): Boolean {
        return modelRefuelNew.providerCreator != "" &&
                modelRefuelNew.fuelType != "" &&
                modelRefuelNew.cost != 0F &&
                modelRefuelNew.fuelAmount != 0
    }

    override fun getModelRefueling(): Refueling {
        return modelRefuelNew
    }

    override fun setModelRefueling(refueling: Refueling) {
        modelRefuelOld = Refueling(refueling.refuelingId, refueling.fuelType, refueling.fuelAmount, refueling.cost, refueling.addressCreator, refueling.providerCreator)
        modelRefuelNew = refueling
    }

    override fun getModelPlace(): Place {
        return modelPlace
    }

    override fun setModelPlace(place: Place) {
        modelPlace = place
    }

    override fun onMapReady(p0: GoogleMap?) {
        Log.d(TAG, "onMapReady: here")
        view.createMarker(LatLng(modelPlace.location.lat, modelPlace.location.lng))
    }

    override fun onMarkerDragEnd(p0: Marker) {
        modelPlace.location = com.google.maps.model.LatLng(p0.position.latitude, p0.position.longitude)
        ReverseGeocoding(context, this, p0.position)
    }

    override fun onMarkerDragStart(p0: Marker?) {

    }

    override fun onMarkerDrag(p0: Marker?) {

    }

    override fun onMapLongClick(p0: LatLng) {
        modelPlace.location = com.google.maps.model.LatLng(modelPlace.location.lat, modelPlace.location.lng)
        ReverseGeocoding(context, this, p0)
    }

    override fun update(placeName: String) {
        modelPlace.address = placeName
        modelRefuelNew.addressCreator = placeName
        view.createMarker(LatLng(modelPlace.location.lat, modelPlace.location.lng))
    }
}