package com.connection.driverModule.driverPkgdetails;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
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
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.connection.ApiPkg.ApiServices;
import com.connection.ApiPkg.RetrofitClient;
import com.connection.R;
import com.connection.driverModule.driverPkgdetails.DriverCreateRouteModlePkg.DriverCreateRouteModle;
import com.connection.driverModule.driverPkgdetails.autoCompleteAdapterPkg.AutoCompleteAdapter;
import com.connection.driverModule.driverPkgdetails.getRoutePkg.GetRouteDetailModle;
import com.connection.driverModule.homedriverpkg.HomeFragmentDriver;
import com.connection.utility.AppSession;
import com.connection.utility.CheckNetwork;
import com.connection.utility.Constants;
import com.connection.utility.CustomProgressbar;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StartSharingFragment extends Fragment implements View.OnClickListener {
    AppCompatImageView ivbackId;
    RelativeLayout rlStartSharingRideId;
    private ApiServices apiServices;
    private AutoCompleteTextView etCurrentLocation, etDropLocation;
    private PlacesClient placesClient, placesClient_sec;
    AutoCompleteAdapter adapter, adapter_dest;
    private String userid;
    private AppCompatEditText etAmountPerKm, etNoOfSeat;
    private String sourceLat, sourceLng, sourceLocation;
    private String destLat, destLng, destLocation;
    private static Animation shakeAnimation;

    private AddTitleInterFace mCallback;

    public interface AddTitleInterFace {
        void passData(String data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (AddTitleInterFace) getActivity();
        } catch (Exception e) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start_sharing_ride, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        apiServices = RetrofitClient.getClient().create(ApiServices.class);
        userid = AppSession.getStringPreferences(getActivity(), Constants.USER_ID);
        mCallback.passData("Request");
        init(view);
        if (CheckNetwork.isNetAvailable(getActivity())) {
            get_route();
        } else {
            Toast.makeText(getActivity(), "Check Network Connection", Toast.LENGTH_LONG).show();
        }
        return view;
    }

    private void init(View view) {
        etAmountPerKm = view.findViewById(R.id.etAmountPerKmId);
        etNoOfSeat = view.findViewById(R.id.etNoOfSeatKmId);
        etDropLocation = view.findViewById(R.id.etDropLocationId);
        etCurrentLocation = view.findViewById(R.id.etCurrentLocationId);
        rlStartSharingRideId = view.findViewById(R.id.rlStartSharingRideId);
        rlStartSharingRideId.setOnClickListener(this);
        ivbackId = view.findViewById(R.id.ivbackId);
        ivbackId.setOnClickListener(this);

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
            case R.id.ivbackId:
                break;
            case R.id.rlStartSharingRideId:
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
                } else if (etAmountPerKm.getText().toString().isEmpty()) {
                    etAmountPerKm.requestFocus();
                    etAmountPerKm.setError("Required ");
                    etAmountPerKm.startAnimation(shakeAnimation);
                    etAmountPerKm.getBackground().mutate().setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                } else if (etNoOfSeat.getText().toString().isEmpty()) {
                    etNoOfSeat.requestFocus();
                    etNoOfSeat.setError("Required ");
                    etNoOfSeat.startAnimation(shakeAnimation);
                    etNoOfSeat.getBackground().mutate().setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                } else {
                    if (CheckNetwork.isNetAvailable(getActivity())) {
                        createRoute();
                    } else {
                        Toast.makeText(getActivity(), "Check Network Connection", Toast.LENGTH_LONG).show();
                    }
                }

                break;
        }
    }

    public void addFragment(Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.flHomeId, fragment);
        ft.commitAllowingStateLoss();
    }


    private void createRoute() {
        CustomProgressbar.showProgressBar(getActivity(), false);
        apiServices.crete_a_route(userid, sourceLat, sourceLng,
                destLat, destLng, etCurrentLocation.getText().toString(), etDropLocation.getText().toString(),
                etAmountPerKm.getText().toString(), etNoOfSeat.getText().toString()).enqueue(new Callback<DriverCreateRouteModle>() {
            @Override
            public void onResponse(Call<DriverCreateRouteModle> call, Response<DriverCreateRouteModle> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    DriverCreateRouteModle driverCreateRouteModle = response.body();
                    if (driverCreateRouteModle.getStatus()) {
                        addFragment(new HomeFragmentDriver());
                    } else {
                        Toast.makeText(getActivity(), "" + driverCreateRouteModle.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            CustomProgressbar.hideProgressBar();
                            String message = jsonObject.getString("message");
                            Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DriverCreateRouteModle> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }

        });
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
                            etDropLocation.setSelection(etCurrentLocation.getText().length());
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

    private void get_route() {
        CustomProgressbar.showProgressBar(getActivity(), false);
        apiServices.get_route(userid).enqueue(new Callback<GetRouteDetailModle>() {
            @Override
            public void onResponse(Call<GetRouteDetailModle> call, Response<GetRouteDetailModle> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    GetRouteDetailModle getRouteDetailModle = response.body();
                    if (getRouteDetailModle.getStatus()) {

                        etAmountPerKm.setText(getRouteDetailModle.getData().get(0).getAmountPerKm());
                        etNoOfSeat.setText(getRouteDetailModle.getData().get(0).getTotalSeats());
                        etCurrentLocation.setText(getRouteDetailModle.getData().get(0).getCurrentLocation());
                        etDropLocation.setText(getRouteDetailModle.getData().get(0).getDropLocation());
                        sourceLat = getRouteDetailModle.getData().get(0).getCurrentLocationLat();
                        sourceLng = getRouteDetailModle.getData().get(0).getCurrentLocationLong();
                        destLat = getRouteDetailModle.getData().get(0).getDropLocationLat();
                        destLng = getRouteDetailModle.getData().get(0).getDropLocationLong();
                    } else {
                        Toast.makeText(getActivity(), "" + getRouteDetailModle.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            CustomProgressbar.hideProgressBar();
                            String message = jsonObject.getString("message");
                            Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetRouteDetailModle> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }

        });
    }

}

