package de.hsos.sportwetter.ui.maps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.location.Criteria;


import de.hsos.sportwetter.MainActivity;
import de.hsos.sportwetter.R;

import static androidx.core.content.ContextCompat.getSystemService;

//help from: https://stackoverflow.com/questions/24320989/locationmanager-locationmanager-this-getsystemservicecontext-location-servi

public class MapFragment extends Fragment implements LocationListener {
    private static final int ACCESS_FINE_LOCATION_CODE = 100;
    LocationManager locationManager;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;
    android.location.Location location; // location
    //Default loation = Osnabrueck
    double latitude = 52.27264; // latitude
    double longitude = 8.0498; // longitude
    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            try{
                locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
                isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                if(isGPSEnabled){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES,this);
                        Log.d("GPS: ", "enabled");
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                }
                if(isGPSEnabled && isNetworkEnabled){
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("NETWORK: ", "enabled");
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                }
            }catch (Exception e){
                Log.d("MAP-LOCATIONMANAGER: ", "Exception");
            }
        }else{
            ActivityCompat.requestPermissions(this.getActivity(),
                    new String[]{ Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_FINE_LOCATION_CODE);
        }
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }
    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng pos = new LatLng(latitude, longitude);
            googleMap.addMarker(new MarkerOptions()
                    .position(pos)
                    .title("Aktuelle position"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(pos));
        }
    };
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
              (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.maps);
        if (mapFragment != null) {
           mapFragment.getMapAsync(callback);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}