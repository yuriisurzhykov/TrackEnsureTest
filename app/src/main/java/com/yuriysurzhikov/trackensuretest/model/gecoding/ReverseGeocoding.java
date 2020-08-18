package com.yuriysurzhikov.trackensuretest.model.gecoding;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.yuriysurzhikov.trackensuretest.utils.Observer;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ReverseGeocoding {

    private String name;
    private Observer observer;

    public ReverseGeocoding(Context context, Observer observer, LatLng... latLng) {
        new ReverseGeocodingTask(context, observer).execute(latLng);
    }

    private static class ReverseGeocodingTask extends AsyncTask<LatLng, Void, List<Address>> {

        private static final String TAG = "ReverseGeocodingTask";
        private List<Address> addresses;
        private int result = 1;
        private Context context;
        private Address address;
        private Observer observer;

        public ReverseGeocodingTask(Context context, Observer observer) {
            this.context = context;
            this.observer = observer;
        }

        // Finding address using reverse geocoding
        @Override
        protected List<Address> doInBackground(LatLng... params) {
            Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);
            try {
                addresses = geocoder.getFromLocation(params[0].latitude, params[0].longitude, result);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addresses;
        }

        @Override
        protected void onPostExecute(List<Address> addressText) {
            super.onPostExecute(addresses);
            if (addresses == null || addresses.size() == 0) {
                Toast.makeText(context, "No location found", Toast.LENGTH_SHORT).show();
                return;
            }
            address = addresses.get(0);
            String aLine = "";
            for (int addr = 0; addr <= address.getMaxAddressLineIndex() - 2; addr++) {
                aLine = aLine.length() > 0 ? aLine + ", "
                        + (address.getAddressLine(addr)) : String
                        .valueOf(address.getAddressLine(addr));
            }
            address.setAddressLine(0, aLine);
            String returnedAddress =
                    address.getAdminArea() + "," +
                    address.getLocality() + "," +
                    address.getThoroughfare() + "," +
                    address.getFeatureName() + "," +
                    address.getCountryName();
            // Setting the title for the marker.
            // This will be displayed on taping the marker
            Log.d(TAG, "onPostExecute: " + returnedAddress);
            observer.update(returnedAddress);
        }
    }
}
