package com.yuriysurzhikov.trackensuretest.presenter.contracts

import android.os.Bundle

open interface MainActivityContract {
    interface Model {

    }

    interface View {
        fun onCreate(bundle: Bundle?)
    }

    interface Presenter {

    }
}