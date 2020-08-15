package com.yuriysurzhikov.trackensuretest.presenter.contracts

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yuriysurzhikov.trackensuretest.model.entities.Place
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling

interface AddingActivityContract {

    interface Model {
        
    }
    
    interface View {
        fun startLoading()
        fun stopLoading()
        fun setNextButtonActive()
        fun showError(message: String)
        fun showSuccess(message: String)
        fun openBottomSheet(view: android.view.View)
        fun closeBottomSheet()
        fun closeActivity()
        fun createMarker(latlng: LatLng)
        fun save(view: android.view.View)
    }
    
    interface Presenter: OnMapReadyCallback, GoogleMap.OnMarkerDragListener {
        fun checkFields(): Boolean
        fun saveRefuelingNote()
        fun changeProvider(name: String)
        fun changeFuelType(type: String)
        fun changeAmount(amount: Int)
        fun changeCost(cost: Float)
        fun setOpenSheetButton(activity: BottomSheetDialogFragment)
        fun getModelRefueling(): Refueling
        fun setModelRefueling(refueling: Refueling)
        fun getModelPlace(): Place
        fun setModelPlace(place: Place)
        fun setLocation(latlng: LatLng)
    }
}