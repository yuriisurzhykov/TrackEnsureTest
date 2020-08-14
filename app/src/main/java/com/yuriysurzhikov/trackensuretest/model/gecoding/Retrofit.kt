package com.yuriysurzhikov.trackensuretest.model.gecoding

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    lateinit var retrofit: Retrofit

    companion object Builder {
        val GEOCODING_URL: String = "https://maps.googleapis.com/maps/api/geocode"
        fun geocodingRetrofit(): Retrofit {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(Gson()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createAsync())
                    .baseUrl(GEOCODING_URL)
                    .build()
            return retrofit
        }
    }
}