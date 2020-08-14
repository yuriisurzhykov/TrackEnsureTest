package com.yuriysurzhikov.trackensuretest.model.gecoding;

import com.google.android.gms.maps.model.LatLng;
import com.yuriysurzhikov.trackensuretest.model.entities.ReverseGeoCodingResult;

import retrofit2.Call;

public class GeoCodingImpl {
    public static Call<ReverseGeoCodingResult> getGoeCode(LatLng latLng, String apiKey) {
        return Retrofit.Builder.geocodingRetrofit().create(GeoCodingAPI.class).getReverseGeoCoding(latLng, apiKey);
    }
}
