package com.yuriysurzhikov.trackensuretest.presenter

import android.content.Context
import android.util.Log
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yuriysurzhikov.trackensuretest.model.MainRepository
import com.yuriysurzhikov.trackensuretest.model.entities.Location
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling
import com.yuriysurzhikov.trackensuretest.model.gecoding.ReverseGeocoding
import com.yuriysurzhikov.trackensuretest.presenter.contracts.AddingActivityContract
import com.yuriysurzhikov.trackensuretest.utils.Observer

class AddingActivityPresenter(val view: AddingActivityContract.View, val context: Context) : AddingActivityContract.Presenter, Observer {

    val BOTTOM_SHEET_TAG = "bottom_sheet_dialog"
    private var model: Refueling = Refueling()
    var location: Location = Location()
    val TAG = "AddingActivityPresen"


    override fun checkFields(): Boolean {
        return model.cost != 0F && model.fuelAmount != 0F && model.fuelType != "" && model.provider != ""
    }

    override fun saveRefuelingNote() {
        if(checkFields()) {
            Log.d(TAG, "saveRefuelingNote: " + model.location.placeName)
            MainRepository.getInstance().addRefuelingNote(model)
            view.showSuccess("Data successfully saved!")
            view.closeActivity()
        } else {
            view.showError("Not all fields are filled!")
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

    override fun setOpenSheetButton(activity: BottomSheetDialogFragment) {

    }

    override fun getModel(): Refueling {
        return model
    }

    override fun setModel(refueling: Refueling) {
        model = refueling
    }

    override fun setLocation(latlng: LatLng) {
        model.location.lng = latlng.longitude
        model.location.lat = latlng.latitude
        ReverseGeocoding(context, this, latlng)
    }

    override fun onMapReady(p0: GoogleMap?) {

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

    override fun update(placeName: String) {
        model.location.placeName = placeName
        view.createMarker(LatLng(model.location.lat, model.location.lng))
    }
}