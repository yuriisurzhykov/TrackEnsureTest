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

    private var modelRefuel = Refueling()
    private var modelPlace = Place()
    private val TAG = "EditingActivityPresent"

    override fun loadLocation(location: LatLng) {
        modelPlace.location = location
        ReverseGeocoding(context, this, location)
    }

    override fun updateRefueling() {
        if(checkFields()) {
            MainRepository.getInstance().updateRefuelingNote(modelRefuel, modelPlace)
            view.showMessage("Data has been successfully saved!")
            view.closeActivity()
        } else {
            view.showMessage("Not all fields are filled!")
        }
    }

    override fun changeProvider(name: String) {
        modelRefuel.providerCreator = name
        modelPlace.provider = name
    }

    override fun changeFuelType(type: String) {
        modelRefuel.fuelType = type
    }

    override fun changeAmount(amount: Int) {
        modelRefuel.fuelAmount = amount
    }

    override fun changeCost(cost: Float) {
        modelRefuel.cost = cost
    }

    override fun checkFields(): Boolean {
        return modelRefuel.providerCreator != "" &&
                modelRefuel.fuelType != "" &&
                modelRefuel.cost != 0F &&
                modelRefuel.fuelAmount != 0
    }

    override fun getModelRefueling(): Refueling {
        return modelRefuel
    }

    override fun setModelRefueling(refueling: Refueling) {
        modelRefuel = refueling
    }

    override fun getModelPlace(): Place {
        return modelPlace
    }

    override fun setModelPlace(place: Place) {
        modelPlace = place
    }

    override fun onMapReady(p0: GoogleMap?) {
        Log.d(TAG, "onMapReady: here")
        view.createMarker(modelPlace.location)
    }

    override fun onMarkerDragEnd(p0: Marker) {
        modelPlace.location = p0.position
        ReverseGeocoding(context, this, p0.position)
    }

    override fun onMarkerDragStart(p0: Marker?) {

    }

    override fun onMarkerDrag(p0: Marker?) {

    }

    override fun onMapLongClick(p0: LatLng) {
        modelPlace.location = p0
        ReverseGeocoding(context, this, p0)
    }

    override fun update(placeName: String) {
        modelPlace.address = placeName
        modelRefuel.addressCreator = placeName
        view.createMarker(modelPlace.location)
    }
}