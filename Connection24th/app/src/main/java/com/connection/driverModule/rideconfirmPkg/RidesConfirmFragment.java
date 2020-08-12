package com.connection.driverModule.rideconfirmPkg;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.connection.ApiPkg.ApiServices;
import com.connection.ApiPkg.RetrofitClient;
import com.connection.R;
import com.connection.driverModule.driverChatPkg.DriverChatActivity;
import com.connection.driverModule.rideconfirmPkg.raiseModelPkg.RaiseFairModle;
import com.connection.utility.AppSession;
import com.connection.utility.CheckNetwork;
import com.connection.utility.Constants;
import com.connection.utility.CustomProgressbar;
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
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("Registered")
public class RidesConfirmFragment extends Fragment implements
        View.OnClickListener,
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    private RelativeLayout searchRides, rlRaiseFair;
    private GoogleApiClient mGoogleApiClient;
    private Marker mCurrLocationMarker;
    private ApiServices apiServices;
    private String userId, username, receiver_userId, receiver_userName,
            receiver_userProfilePic, receiver_userNumber, receivedSource, receivedDest;
    private AppCompatButton btnToMinus, btnToAddition;
    private AppCompatTextView tvDynamiceAmout;
    int count = 10;
    private static final int PERMISSION_CALL_PHONE = 100;
    private static final int SELECT_PHONE = 101;
    private AppCompatEditText etCurrentLocation, etDestination;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_driver_ride_confirm, container, false);
        apiServices = RetrofitClient.getClient().create(ApiServices.class);
        userId = AppSession.getStringPreferences(getActivity(), Constants.USER_ID);
        username = AppSession.getStringPreferences(getActivity(), Constants.USERNAME);
        receiver_userId = getArguments().getString("userId");
        receiver_userName = getArguments().getString("userName");
        receiver_userProfilePic = getArguments().getString("userProfilePic");
        receiver_userNumber = getArguments().getString("userNumber");
        receivedSource = getArguments().getString("current");
        receivedDest = getArguments().getString("destination");
        init(view);
        etCurrentLocation.setText(receivedSource);
        etDestination.setText(receivedDest);
        return view;
    }

    private void init(View view) {
        checkLocationPermission();

        etCurrentLocation = view.findViewById(R.id.etCurrentLocationId);
        etDestination = view.findViewById(R.id.etDestinationId);

        tvDynamiceAmout = view.findViewById(R.id.tvDynamiceAmoutId);
        btnToAddition = view.findViewById(R.id.btnToAdditionId);
        btnToMinus = view.findViewById(R.id.btnToMinusId);
        rlRaiseFair = view.findViewById(R.id.rlRaiseFairId);
        rlRaiseFair.setOnClickListener(this);
        btnToAddition.setOnClickListener(this);
        btnToMinus.setOnClickListener(this);
        mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permision was granted, yay! Do the
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


    public void confirmationDialoge() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dilog_driver_chat);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        AppCompatButton abchatId = dialog.findViewById(R.id.abchatIdmain);
        AppCompatButton acbCallId = dialog.findViewById(R.id.acbCallId);
        AppCompatImageView ivcrossId = dialog.findViewById(R.id.ivcrossId);
        AppCompatTextView tvAmountConfirmDial = dialog.findViewById(R.id.tvAmountConfirmDialId);
        AppCompatTextView tvuserNameConfirmation = dialog.findViewById(R.id.tvuserNameConfirmationId);
        AppCompatTextView tvConfirmationTag = dialog.findViewById(R.id.tvConfirmationTagId);

        CircleImageView civProfileConfirmedDialoge = dialog.findViewById(R.id.civProfileConfirmedDialogeId);
        tvAmountConfirmDial.setText("Free Amount : " + "&" + count);
        tvuserNameConfirmation.setText(receiver_userName);
        tvConfirmationTag.setText("Congratulation " + username + " has Accepted your offer to start ride with him");

        if (receiver_userProfilePic.isEmpty()) {
        } else {
            Picasso.with(getActivity()).load(RetrofitClient.IMAGE_PATH + receiver_userProfilePic)
                    .resize(200, 200)
                    .into(civProfileConfirmedDialoge);
        }

        abchatId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DriverChatActivity.class);
                intent.putExtra("receiver_userId", receiver_userId);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                dialog.dismiss();
            }
        });

        acbCallId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askPhoneCallPermission();

            }
        });

        ivcrossId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlRaiseFairId:
                if (CheckNetwork.isNetAvailable(getActivity())) {
                    raiseFair();
                } else {
                    Toast.makeText(getActivity(), "Check Network Connection", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnToAdditionId:
                count = count + 10;
                tvDynamiceAmout.setText("$" + count);
                break;
            case R.id.btnToMinusId:
                if (count > 0) {
                    count = count - 10;
                    tvDynamiceAmout.setText("$" + count);
                }
                break;
        }
    }

    private void raiseFair() {
        CustomProgressbar.showProgressBar(getActivity(), false);
        apiServices.raiseFair(receiver_userId, userId, "" + count).enqueue(new Callback<RaiseFairModle>() {
            @Override
            public void onResponse(Call<RaiseFairModle> call, Response<RaiseFairModle> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    RaiseFairModle raiseFairModle = response.body();
                    if (raiseFairModle.getStatus()) {
                        confirmationDialoge();
                    } else {
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
            public void onFailure(Call<RaiseFairModle> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }

        });
    }

    private void askPhoneCallPermission() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE},
                        PERMISSION_CALL_PHONE);
            }
        } else {
            phonecallCode();
        }
    }

    private void phonecallCode() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + receiver_userNumber));
        startActivity(intent);
    }

}
