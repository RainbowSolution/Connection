package com.connection.driverModule.driverPkgdetails;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.connection.ApiPkg.ApiServices;
import com.connection.ApiPkg.RetrofitClient;
import com.connection.R;
import com.connection.driverModule.driverPkgdetails.driverRegreshModlePkg.DriverVerificationModle;
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

@SuppressLint("Registered")
public class DriverRefreshFragment extends Fragment implements View.OnClickListener {
    AppCompatImageView ivbackId;
    RelativeLayout rerefreshId;
    private ApiServices apiServices;
    private String userId;

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
        View view = inflater.inflate(R.layout.driver_very_fication_refresh, container, false);
        apiServices = RetrofitClient.getClient().create(ApiServices.class);
        userId = AppSession.getStringPreferences(getActivity(), Constants.USER_ID);
        try {
            mCallback.passData("Request");
        } catch (Exception e) {
            e.printStackTrace();
        }
        init(view);
        return view;
    }

    private void init(View view) {
        rerefreshId = view.findViewById(R.id.rerefreshId);
        rerefreshId.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivbackId:
                // onBackPressed();
                break;
            case R.id.rerefreshId:
                addFragment(new StartSharingFragment());
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


    private void get_driver_verification_status() {
        CustomProgressbar.showProgressBar(getActivity(), false);
        apiServices.get_driver_verification_status(userId).enqueue(new Callback<DriverVerificationModle>() {
            @Override
            public void onResponse(Call<DriverVerificationModle> call, Response<DriverVerificationModle> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    DriverVerificationModle driverVerificationModle = response.body();
                    if (driverVerificationModle.getStatus()) {
                        if (driverVerificationModle.getData().equalsIgnoreCase("1")) {
                            addFragment(new StartSharingFragment());
                        } else {

                        }
                    } else {
                        Toast.makeText(getActivity(), "" + driverVerificationModle.getMessage(), Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<DriverVerificationModle> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }

        });
    }


}
