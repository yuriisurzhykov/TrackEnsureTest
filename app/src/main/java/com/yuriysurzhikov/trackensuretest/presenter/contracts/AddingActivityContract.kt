package com.yuriysurzhikov.trackensuretest.presenter.contracts

import android.app.Activity
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yuriysurzhikov.trackensuretest.model.entities.Location
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling
import com.yuriysurzhikov.trackensuretest.model.entities.ReverseGeoCodingResult
import retrofit2.Call
import rx.Observable

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
        fun finish()
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
    }
}