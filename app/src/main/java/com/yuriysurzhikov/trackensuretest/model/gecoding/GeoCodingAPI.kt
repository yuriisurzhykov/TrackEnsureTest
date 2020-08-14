package com.yuriysurzhikov.trackensuretest.model.gecoding


import com.google.android.gms.maps.model.LatLng
import com.yuriysurzhikov.trackensuretest.model.entities.ReverseGeoCodingResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoCodingAPI {
    @GET("/json")
    fun getReverseGeoCoding(@Query("latlng") latLng: LatLng, @Query("key")apiKey: String): Call<ReverseGeoCodingResult>
}