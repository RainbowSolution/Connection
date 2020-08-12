package com.connection.userModule.ridesPkg;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.connection.R;
import com.connection.driverModule.driverPkgdetails.autoCompleteAdapterPkg.AutoCompleteAdapter;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.Arrays;
import java.util.List;

public class RidesSarchFragment extends Fragment implements View.OnClickListener, OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    private RelativeLayout searchRides;
    private GoogleApiClient mGoogleApiClient;
    private Marker mCurrLocationMarker;
    private String sourceLat, sourceLng, sourceLocation;
    private String destLat, destLng, destLocation;
    private AutoCompleteTextView etCurrentLocation, etDropLocation;
    private PlacesClient placesClient, placesClient_sec;
    private AutoCompleteAdapter adapter, adapter_dest;
    private RelativeLayout rlPaypal, rlCashDriverSearch;
    private AppCompatTextView tvCashRiderSearch, tvPaypalRiderSearch;
    private static Animation shakeAnimation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ride_search_fragment, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        /// android:windowSoftInputMode="adjustPan|adjustResize"
        init(view);
        return view;
    }

    private void init(View view) {
        rlCashDriverSearch = view.findViewById(R.id.rlCashDriverSearchId);
        tvCashRiderSearch = view.findViewById(R.id.tvCashRiderSearchId);
        rlPaypal = view.findViewById(R.id.rlPaypalId);
        etCurrentLocation = view.findViewById(R.id.etCurrentLocationId);
        tvPaypalRiderSearch = view.findViewById(R.id.tvPaypalRiderSearchId);
        etDropLocation = view.findViewById(R.id.etDroplocationId);
        searchRides = view.findViewById(R.id.rlSearchRideId);
        searchRides.setOnClickListener(this);
        rlPaypal.setOnClickListener(this);
        rlCashDriverSearch.setOnClickListener(this);
        checkLocationPermission();
        mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);


        String apiKey = getString(R.string.google_maps_key);
        // Setup Places Client
        if (!Places.isInitialized()) {
            Places.initialize(getActivity(), apiKey);
        }

        placesClient = Places.createClient(getActivity());
        placesClient_sec = Places.createClient(getActivity());


        etCurrentLocation.setThreshold(1);
        etCurrentLocation.setOnItemClickListener(autocompleteClickListener);
        adapter_dest = new AutoCompleteAdapter(getActivity(), placesClient_sec);
        etCurrentLocation.setAdapter(adapter_dest);

        etDropLocation.setThreshold(1);
        etDropLocation.setOnItemClickListener(autocompleteDropClickListener);
        adapter = new AutoCompleteAdapter(getActivity(), placesClient);
        etDropLocation.setAdapter(adapter);


        shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlCashDriverSearchId:
                tvPaypalRiderSearch.setTextColor(getResources().getColor(R.color.quantum_grey));
                tvCashRiderSearch.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case R.id.rlPaypalId:
                tvPaypalRiderSearch.setTextColor(getResources().getColor(R.color.colorAccent));
                tvCashRiderSearch.setTextColor(getResources().getColor(R.color.quantum_grey));
                break;
            case R.id.rlSearchRideId:
                if (etCurrentLocation.getText().toString().isEmpty()) {
                    etCurrentLocation.requestFocus();
                    etCurrentLocation.setError("Required ");
                    etCurrentLocation.startAnimation(shakeAnimation);
                    etCurrentLocation.getBackground().mutate().setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                } else if (etDropLocation.getText().toString().isEmpty()) {
                    etDropLocation.requestFocus();
                    etDropLocation.setError("Required ");
                    etDropLocation.startAnimation(shakeAnimation);
                    etDropLocation.getBackground().mutate().setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                } else {
                    replaceFragement(new RidesFragment());
                }
                break;
        }

    }

    private void replaceFragement(Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putString("currentLocation", etCurrentLocation.getText().toString());
        bundle.putString("dropLocation", etDropLocation.getText().toString());
        bundle.putString("current_location_lat", sourceLat);
        bundle.putString("current_location_long", sourceLng);
        fragment.setArguments(bundle);
        FragmentTransaction home = getActivity().getSupportFragmentManager().beginTransaction();
        home.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right);
        home.replace(R.id.flHomeId, fragment);
        home.addToBackStack(null);
        home.commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Initialize Google Play Services
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                mGoogleMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        } else {
            buildGoogleApiClient();
            mGoogleMap.setMyLocationEnabled(true);
        }
    }

    private synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);

        //move map camera
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));

    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(getActivity())
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getActivity(), "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private AdapterView.OnItemClickListener autocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            try {
                final AutocompletePrediction item = adapter_dest.getItem(i);
                String placeID = null;
                if (item != null) {
                    placeID = item.getPlaceId();
                }

//                To specify which data types to return, pass an array of Place.Fields in your FetchPlaceRequest
//                Use only those fields which are required.

                List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);

                FetchPlaceRequest request = null;
                if (placeID != null) {
                    request = FetchPlaceRequest.builder(placeID, placeFields).build();
                }

                if (request != null) {
                    placesClient.fetchPlace(request).addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onSuccess(FetchPlaceResponse task) {
                            etCurrentLocation.setText(task.getPlace().getAddress());
                            etCurrentLocation.setSelection(etCurrentLocation.getText().length());
                            sourceLocation = task.getPlace().getAddress();
                            sourceLat = String.valueOf(task.getPlace().getLatLng().latitude);
                            sourceLng = String.valueOf(task.getPlace().getLatLng().longitude);
                            //  etCurrentLocation.setText(task.getPlace().getName() + "\n" + task.getPlace().getAddress());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                            // etCurrentLocation.setText(e.getMessage());
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };

    //autocompleteClickListener
    private AdapterView.OnItemClickListener autocompleteDropClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            try {
                final AutocompletePrediction item = adapter.getItem(i);
                String placeID = null;
                if (item != null) {
                    placeID = item.getPlaceId();
                }
                List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);

                FetchPlaceRequest request = null;
                if (placeID != null) {
                    request = FetchPlaceRequest.builder(placeID, placeFields).build();
                }

                if (request != null) {
                    placesClient.fetchPlace(request).addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onSuccess(FetchPlaceResponse task) {
                            etDropLocation.setText(task.getPlace().getAddress());
                            etDropLocation.setSelection(etDropLocation.getText().length());
                            destLocation = task.getPlace().getAddress();
                            destLat = String.valueOf(task.getPlace().getLatLng().latitude);
                            destLng = String.valueOf(task.getPlace().getLatLng().longitude);
                            //etDropLocation.setText(task.getPlace().getName() + "\n" + task.getPlace().getAddress());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                            // etDropLocation.setText(e.getMessage());
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };

}
