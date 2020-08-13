package com.yuriysurzhikov.trackensuretest.presenter

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.maps.model.LatLng
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling
import com.yuriysurzhikov.trackensuretest.presenter.contracts.AddingActivityContract
import com.yuriysurzhikov.trackensuretest.view.dialogs.AddingBottomSheetDialog

class AddingActivityPresenter(val view: AddingActivityContract.View, val context: Context): AddingActivityContract.Presenter {

    override fun createLocation(latlng: LatLng) {

    }

    override fun checkFields() {
        TODO("Not yet implemented")
    }

    override fun saveRefuelingNote(refueling: Refueling) {
        TODO("Not yet implemented")
    }

    override fun onMapReady(p0: GoogleMap?) {
        TODO("Not yet implemented")
    }
}