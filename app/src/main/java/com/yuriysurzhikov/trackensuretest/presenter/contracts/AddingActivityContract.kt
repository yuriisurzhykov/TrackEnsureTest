package com.yuriysurzhikov.trackensuretest.presenter.contracts

import com.google.android.gms.maps.OnMapReadyCallback
import com.google.maps.model.LatLng
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling

interface AddingActivityContract {

    interface Model {
        
    }
    
    interface View {
        fun startLoading()
        fun stopLoading()
        fun openBottomSheet(view: android.view.View)
    }
    
    interface Presenter: OnMapReadyCallback {
        fun createLocation(latlng: LatLng)
        fun checkFields()
        fun saveRefuelingNote(refueling: Refueling)
    }
}