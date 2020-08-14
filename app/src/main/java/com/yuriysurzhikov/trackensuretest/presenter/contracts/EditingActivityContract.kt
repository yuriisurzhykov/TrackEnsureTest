package com.yuriysurzhikov.trackensuretest.presenter.contracts

import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling

interface EditingActivityContract {
    interface Model {

    }

    interface View {
        fun closeActivity()
        fun openBottomSheet(view: android.view.View)
        fun closeBottomSheet()
        fun createMarker(latLng: LatLng)
        fun showMessage(message: String)
    }

    interface Presenter: OnMapReadyCallback, GoogleMap.OnMarkerDragListener, GoogleMap.OnMapLongClickListener {
        fun loadLocation(location: LatLng)
        fun setModel(refueling: Refueling)
        fun updateRefueling()
        fun changeProvider(name: String)
        fun changeFuelType(type: String)
        fun changeAmount(amount: Float)
        fun changeCost(cost: Float)
        fun getModel(): Refueling
        fun checkFields(): Boolean
    }
}