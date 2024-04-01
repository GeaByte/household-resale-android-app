package com.example.g1_csis3175_002;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

public class LocationHelper {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    private Context context;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;

    private Geocoder geocoder;

    public LocationHelper(Context context) {
        this.context = context;
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        geocoder = new Geocoder(context);
    }

    public interface LocationCallbackListener {
        void onLocationAvailable(Location location);
    }

    public void requestLocationUpdates(final LocationCallbackListener listener) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request location permission
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Location lastLocation = locationResult.getLastLocation();
                listener.onLocationAvailable(lastLocation);
            }
        };

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(600000); // 10 minutes

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    public void stopLocationUpdates() {
        if (locationCallback != null) {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        }
    }

    //convert item's address to Geographical coordinates
    public ArrayList<Double> convertToGeo(String addressString){
        try {
            // Use Geocoder to get a list of Addresses from the provided address string
            List<Address> addresses = geocoder.getFromLocationName(addressString, 1);

            if (addresses != null && !addresses.isEmpty()) {
                // Get the first address from the list
                Address address = addresses.get(0);

                // Retrieve latitude and longitude from the address object
                double latitude = address.getLatitude();
                double longitude = address.getLongitude();

                // Now you have the latitude and longitude coordinates
                ArrayList<Double> coordinates = new ArrayList();
                coordinates.add(latitude);
                coordinates.add(longitude);
                return coordinates;
            }
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }
        return null;
    }

    // Method to calculate distance between two points using Haversine formula

}