package com.yuriysurzhikov.trackensuretest.presenter.contracts

import com.google.maps.model.LatLng

interface AddingActivityContract {
    interface Model {
        
    }
    
    interface View {
        fun openBottomSheet()
        fun startLoading()
        fun stopLoading()
    }
    
    interface Presenter {
        fun createLocation(latlng: LatLng)
        fun checkFields()
    }
}