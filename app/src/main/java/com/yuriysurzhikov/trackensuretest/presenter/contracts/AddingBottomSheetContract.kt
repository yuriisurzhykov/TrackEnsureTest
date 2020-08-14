package com.yuriysurzhikov.trackensuretest.presenter.contracts

import android.view.View
import com.yuriysurzhikov.trackensuretest.model.entities.Refueling

interface AddingBottomSheetContract {
    interface Model {

    }
    interface View {
        fun showErrorMessage()
        fun startLoading()
        fun stopLoading()
    }
    interface Presenter {
        fun uploadRefuelingNote(refueling: Refueling)
    }
}