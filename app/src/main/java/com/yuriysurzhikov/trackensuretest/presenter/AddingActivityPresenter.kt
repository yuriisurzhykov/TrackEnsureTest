package com.yuriysurzhikov.trackensuretest.presenter

import android.content.Context
import android.util.Log
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yuriysurzhikov.trackensuretest.model.MainRepository
import com.yuriysurzhikov.trackensuretest.model.entities.Location
import com.yuriysurzhikov.trackensuretest.model.entities.Place
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling
import com.yuriysurzhikov.trackensuretest.model.gecoding.ReverseGeocoding
import com.yuriysurzhikov.trackensuretest.presenter.contracts.AddingActivityContract
import com.yuriysurzhikov.trackensuretest.utils.Observer

class AddingActivityPresenter(val view: AddingActivityContract.View, val context: Context) : AddingActivityContract.Presenter, Observer {

    val BOTTOM_SHEET_TAG = "bottom_sheet_dialog"
    private var modelRefuel: Refueling = Refueling()
    private var modelPlace: Place = Place()
    val TAG = "AddingActivityPresen"


    override fun checkFields(): Boolean {
        return modelRefuel.cost != 0F && modelRefuel.fuelAmount != 0 && modelRefuel.fuelType != "" && modelRefuel.providerName != ""
    }

    override fun saveRefuelingNote() {
        if(checkFields()) {
            Log.d(TAG, "saveRefuelingNote: " + modelRefuel.providerName)
            MainRepository.getInstance().addRefuelingNote(modelPlace, modelRefuel)
            view.showSuccess("Data successfully saved!")
            view.closeActivity()
        } else {
            view.showError("Not all fields are filled!")
        }
    }

    override fun changeProvider(name: String) {
        modelRefuel.providerName = name
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

    override fun setOpenSheetButton(activity: BottomSheetDialogFragment) {

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

    override fun setLocation(latlng: LatLng) {
        modelPlace.location = latlng
        ReverseGeocoding(context, this, latlng)
    }

    override fun onMapReady(p0: GoogleMap?) {

    }

    override fun onMarkerDragEnd(p0: Marker) {
        modelPlace.location = p0.position
        ReverseGeocoding(context, this, p0.position)
    }

    override fun onMarkerDragStart(p0: Marker?) {

    }

    override fun onMarkerDrag(p0: Marker?) {

    }

    override fun update(placeName: String) {
        modelPlace.address = placeName
        view.createMarker(modelPlace.location)
    }
}