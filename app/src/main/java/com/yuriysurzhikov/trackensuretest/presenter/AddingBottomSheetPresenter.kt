package com.yuriysurzhikov.trackensuretest.presenter

import android.content.Context
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling
import com.yuriysurzhikov.trackensuretest.presenter.contracts.AddingBottomSheetContract

class AddingBottomSheetPresenter(view: AddingBottomSheetContract.View, context: Context): AddingBottomSheetContract.Presenter {

    init {
        var refueling = Refueling()
    }

    override fun onCreate() {
        TODO("Not yet implemented")
    }


}