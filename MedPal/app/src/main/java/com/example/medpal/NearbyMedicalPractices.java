package com.example.medpal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class NearbyMedicalPractices extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;
    double latitude, longitude;
    int PROXIMITY_RADIUS = 10000;
    public static final int REQUEST_LOCATION_CODE = 99;
    private GoogleApiClient client;
    private Location lastLocation;
    private Marker currentLocationMarker;
    private LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_medical_practices);

        //Checking Location Permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //Checking if user has given permission to use location, if not asks user for permission.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){

        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                   if (client == null) {
                       buildGoogleApiClient();
                   }
                    mMap.setMyLocationEnabled(true);
                }
            }
            else { //If users denys permission to use Location.
                Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) { //Checks if Location permissions are granted
            buildGoogleApiClient(); //If granted builds googleApiClient
            mMap.setMyLocationEnabled(true); //Loads the map with the location of user
        }
    }

    //Builds the googleApiClient
    protected synchronized void buildGoogleApiClient() {
        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        client.connect();
    }

    @Override //Checks if users location has updated
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude(); //gets latitude of location
        longitude = location.getLongitude(); //gets longitude of location
        lastLocation = location; //gets the users lastLocation

        if (currentLocationMarker != null) {

            currentLocationMarker.remove();

        }
        Log.d("lat = ",""+ latitude);
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        currentLocationMarker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(10));

        if (client != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(client, (com.google.android.gms.location.LocationListener) this);

        }
    }


    public void onClick (View v) {
        Object dataTransfer[] = new Object[2];
        GetNearByPlaces getNearByPlaces = new GetNearByPlaces();

        switch (v.getId()) {
            case R.id.search_location:
                EditText searchLocation = findViewById(R.id.search_location);
                String location = searchLocation.getText().toString();
                List<Address> addressList;

                if(!location.equals("")) {
                    Geocoder geocoder = new Geocoder(this);

                    try {
                        addressList = geocoder.getFromLocationName(location, 5);

                        if (addressList != null) {
                            for (int i = 0; i <addressList.size(); i++) {

                                LatLng latLng = new LatLng(addressList.get(i).getLatitude(), addressList.get(i).getLongitude());
                                MarkerOptions markerOptions = new MarkerOptions();
                                markerOptions.position(latLng);
                                markerOptions.title(location);
                                mMap.addMarker(markerOptions);
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case R.id.btnMedical_practice:
                mMap.clear();
                String medical_practice = "medical practices";
                String url = getUrl(latitude, longitude, medical_practice);
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

                getNearByPlaces.execute(dataTransfer);
                Toast.makeText(NearbyMedicalPractices.this, "Showing nearby Medical Practices", Toast.LENGTH_SHORT ).show();
                break;
            case R.id.btnHospital:
                mMap.clear();
                String hospital = "hospital";
                url = getUrl(latitude, longitude, hospital);
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

                getNearByPlaces.execute(dataTransfer);
                Toast.makeText(NearbyMedicalPractices.this, "Showing nearby Hospitals", Toast.LENGTH_SHORT ).show();
                break;
            case R.id.btnEmergencyRoom:
                mMap.clear();
                String emergency_room = "emergency room";
                url = getUrl(latitude, longitude, emergency_room);
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

                getNearByPlaces.execute(dataTransfer);
                Toast.makeText(NearbyMedicalPractices.this, "Showing nearby Emergency Rooms", Toast.LENGTH_SHORT ).show();
                break;
        }
    }

    private String getUrl(double latitude, double longitude, String nearbyPlace) {

        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location="+latitude+","+longitude);
        googlePlaceUrl.append("&radius="+PROXIMITY_RADIUS);
        googlePlaceUrl.append("&type="+nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key="+"AIzaSyBMsrB5MbKENEyKYe9Ihz_zeFu5RY2etUo");

        Log.d("NearbyMedicalPractices", "url = "+googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        //Interval set to 1000 or 1 millisecond
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        //Checks if Location Services granted and request location updates
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, (com.google.android.gms.location.LocationListener) this);
        }
    }


    //Checks if the user has Location permissions for the application
    public boolean checkLocationPermission()
    {
        //Checks if permission to access fine_location is granted.
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)  != PackageManager.PERMISSION_GRANTED )
        {
            //Checks if permission has been granted by user.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                //Allows manifest to access location of device.
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
            }
            return false;

        }
        else
            return true;
    }


    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}
