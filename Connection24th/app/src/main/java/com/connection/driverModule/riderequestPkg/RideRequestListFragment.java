package com.connection.driverModule.riderequestPkg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.ApiPkg.ApiServices;
import com.connection.ApiPkg.RetrofitClient;
import com.connection.R;
import com.connection.driverModule.rideconfirmPkg.RidesConfirmFragment;
import com.connection.driverModule.riderequestPkg.adapterPkg.RideRequestAdapter;
import com.connection.driverModule.riderequestPkg.requstListModlePkg.UserRequest;
import com.connection.driverModule.riderequestPkg.requstListModlePkg.UserRequestListModle;
import com.connection.utility.AppSession;
import com.connection.utility.CheckNetwork;
import com.connection.utility.Constants;
import com.connection.utility.CustomProgressbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RideRequestListFragment extends Fragment implements RideRequestAdapter.RideRequestOnClickListener, View.OnClickListener {
    private RecyclerView rvriderequestId;
    private RideRequestAdapter rideRequestAdapter;
    private AppCompatImageView ivbackId;
    private RelativeLayout rlEditLocation;
    private ApiServices apiServices;
    private static Animation shakeAnimation;
    private String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_driver_ride_request, container, false);
        apiServices = RetrofitClient.getClient().create(ApiServices.class);
        userId = AppSession.getStringPreferences(getActivity(), Constants.USER_ID);
        init(view);
        if (CheckNetwork.isNetAvailable(getActivity())) {
            user_request_to_driver_list();
        } else {
            Toast.makeText(getActivity(), "Check Network Connection", Toast.LENGTH_LONG).show();
        }
        return view;
    }

    private void init(View view) {
        ivbackId = view.findViewById(R.id.ivbackId);
        rvriderequestId = view.findViewById(R.id.rvriderequestId);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvriderequestId.setLayoutManager(layoutManager);
        rideRequestAdapter = new RideRequestAdapter(getActivity(), this);
        rvriderequestId.setAdapter(rideRequestAdapter);
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
    }

    @Override
    public void onClick(View view, int position, UserRequest userRequest) {
        switch (view.getId()) {
            case R.id.rebidnowId:
                replaceFragement(new RidesConfirmFragment(), userRequest);
                break;
        }
    }

    private void replaceFragement(Fragment fragment, UserRequest userRequest) {
        Bundle args = new Bundle();
        args.putString("userId", userRequest.getUserId());
        args.putString("userName", userRequest.getName());
        args.putString("userProfilePic", userRequest.getProfilePic());
        args.putString("userNumber", userRequest.getMobileNumber());
        args.putString("current", userRequest.getSourceLocation());
        args.putString("destination", userRequest.getDestinationLocation());
        fragment.setArguments(args);
        FragmentTransaction home = getActivity().getSupportFragmentManager().beginTransaction();
        home.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right);
        home.replace(R.id.flHomeId, fragment);
        home.addToBackStack(null);
        home.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlEditLocationId:
                // onBackPressed();
                break;
        }
    }

    private void user_request_to_driver_list() {
        CustomProgressbar.showProgressBar(getActivity(), false);
        apiServices.user_request_to_driver_list("1").enqueue(new Callback<UserRequestListModle>() {
            @Override
            public void onResponse(Call<UserRequestListModle> call, Response<UserRequestListModle> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    UserRequestListModle userRequestListModle = response.body();
                    if (userRequestListModle.getStatus()) {
                        rideRequestAdapter.addRequestList(userRequestListModle.getUserRequest());
                    } else {
                        // Toast.makeText(getActivity(), "" + userRequestListModle.g(), Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<UserRequestListModle> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }

        });
    }

}
