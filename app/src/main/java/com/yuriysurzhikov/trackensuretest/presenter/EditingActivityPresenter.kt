package com.yuriysurzhikov.trackensuretest.presenter

import android.content.Context
import android.util.Log
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.yuriysurzhikov.trackensuretest.model.MainRepository
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling
import com.yuriysurzhikov.trackensuretest.model.gecoding.ReverseGeocoding
import com.yuriysurzhikov.trackensuretest.presenter.contracts.EditingActivityContract
import com.yuriysurzhikov.trackensuretest.utils.Observer

class EditingActivityPresenter(val view: EditingActivityContract.View, val context: Context) : EditingActivityContract.Presenter, Observer {

    private var model = Refueling()
    private val TAG = "EditingActivityPresent"

    override fun loadLocation(location: LatLng) {
        model.location.lat = location.latitude
        model.location.lng = location.longitude
        ReverseGeocoding(context, this, location)
    }

    override fun setModel(refueling: Refueling) {
        model = refueling
    }

    override fun updateRefueling() {
        if(checkFields()) {
            Log.d(TAG, "saveRefuelingNote: " + model.location.placeName)
            MainRepository.getInstance().updateRefuelingNote(model)
            view.showMessage("Data has been successfully changed")
            view.closeActivity()
        } else {
            view.showMessage("Not all fields are filled!")
        }
    }

    override fun changeProvider(name: String) {
        model.provider = name
    }

    override fun changeFuelType(type: String) {
        model.fuelType = type
    }

    override fun changeAmount(amount: Float) {
        model.fuelAmount = amount
    }

    override fun changeCost(cost: Float) {
        model.cost = cost
    }

    override fun getModel(): Refueling {
        return model
    }

    override fun checkFields(): Boolean {
        return model.provider != "" &&
                model.fuelType != "" &&
                model.cost != 0F &&
                model.fuelAmount != 0F
    }

    override fun onMapReady(p0: GoogleMap?) {
        Log.d(TAG, "onMapReady: here")
        view.createMarker(LatLng(model.location.lat, model.location.lng))
    }

    override fun onMarkerDragEnd(p0: Marker) {
        model.location.lng = p0.position.longitude
        model.location.lat = p0.position.latitude
        ReverseGeocoding(context, this, p0.position)
    }

    override fun onMarkerDragStart(p0: Marker?) {

    }

    override fun onMarkerDrag(p0: Marker?) {

    }

    override fun onMapLongClick(p0: LatLng) {
        model.location.lng = p0.longitude
        model.location.lat = p0.latitude
        ReverseGeocoding(context, this, p0)
    }

    override fun update(placeName: String) {
        Log.d(TAG, "update: location name updated")
        model.location.placeName = placeName
        view.createMarker(LatLng(model.location.lat, model.location.lng))
    }
}