package com.shermansthill.nagla;

import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ssthill on 4/29/2017.
 */

public class GetNearbyStoresData extends AsyncTask<Object, String, String> {

    String googlePlacesData;
    GoogleMap mMap;
    String url;

    @Override
    protected String doInBackground(Object... params) {

        try {
            Log.d("GetNearbyStoresData", "inside doInBackground");

            mMap = (GoogleMap) params[0];
            url = (String) params[1];

            UrlConnection urlConnection = new UrlConnection();
            googlePlacesData = urlConnection.readUrl(url);

            Log.d("GetNearbyStoresData", "done with doInBackground");
        } catch (Exception e) {
            Log.d("GPlacesReadAsyncTask", e.toString());
        }
        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d("GetNearbyStoresData", "inside onPostExecute");

        List<HashMap<String, String>> nearbyStoresList = null;
        MapsDataParser mapsDataParser = new MapsDataParser();

        nearbyStoresList = mapsDataParser.parse(s);
        showNearbyStores(nearbyStoresList);

        Log.d("GetNearbyStoresData", "done with onPostExecute");
    }

    private void showNearbyStores(List<HashMap<String, String>> nearbyStoresList) {
        for (HashMap<String, String> googlePlace : nearbyStoresList) {
            MarkerOptions markerOptions;

            double latitude = Double.parseDouble(googlePlace.get("lat"));
            double longitude = Double.parseDouble(googlePlace.get("lng"));
            String storeName = googlePlace.get("place_name");
            String vicinity = googlePlace.get("vicinity");

            LatLng latLng = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions()
                    .title(storeName + " : " + vicinity)
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            // Move the map camera
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
        }
    }
}
