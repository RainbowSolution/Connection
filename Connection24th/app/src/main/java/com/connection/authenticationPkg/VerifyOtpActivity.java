package com.connection.authenticationPkg;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.connection.ApiPkg.ApiServices;
import com.connection.ApiPkg.RetrofitClient;
import com.connection.MainActivity;
import com.connection.R;
import com.connection.authenticationPkg.authenticationModle.otpModlePkg.OtpResponseModle;
import com.connection.utility.AppSession;
import com.connection.utility.Constants;
import com.connection.utility.CustomProgressbar;
import com.connection.utility.CustomToast;
import com.connection.utility.NetworkUtility;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtpActivity extends BasicActivity implements View.OnClickListener {
    private static Animation shakeAnimation;
    private RelativeLayout rlEditNumberVerify, rlVerifyOtpVerity;
    private TextInputEditText etOtpUpId;
    private String otp, number, token, name;
    private AppCompatTextView tvEnterOtp, tvResendTimerOtpId;
    private ApiServices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        apiServices = RetrofitClient.getClient().create(ApiServices.class);
        Intent intent = getIntent();
        otp = intent.getStringExtra("otp");
        number = intent.getStringExtra("number");
        token = intent.getStringExtra("token");
        name = intent.getStringExtra("name");
        //Log.v("data", otp + "," + number + "," + token + "," + name);
        init();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                etOtpUpId.setText(otp);
                tvEnterOtp.setText(getResources().getString(R.string.CONTINUE));
            }

        }, 4000);


    }

    private void init() {
        rlEditNumberVerify = findViewById(R.id.rlEditNumberVerifyId);
        rlEditNumberVerify.setOnClickListener(this);
        rlVerifyOtpVerity = findViewById(R.id.rlVerifyOtpVerityId);
        rlVerifyOtpVerity.setOnClickListener(this);
        etOtpUpId = findViewById(R.id.etOtpUpId);
        tvEnterOtp = findViewById(R.id.tvEnterOtpId);
        tvResendTimerOtpId = findViewById(R.id.tvResendTimerOtpId);


        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);

        new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {
                long Mmin = (millisUntilFinished / 1000) / 60;
                long Ssec = (millisUntilFinished / 1000) % 60;
                if (Ssec < 10) {
                    tvResendTimerOtpId.setText("0" + Mmin + ":0" + Ssec);
                } else tvResendTimerOtpId.setText("0" + Mmin + ":" + Ssec);
            }

            public void onFinish() {
                tvResendTimerOtpId.setText("00:00");
            }
        }.start();


      /*  FirebaseApp.initializeApp(VerifyOtpActivity.this);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        token = task.getResult().getToken();
                        Log.d("token", token);
                    }
                });*/


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlEditNumberVerifyId:
                onBackPressed();
                break;
            case R.id.rlVerifyOtpVerityId:
                if (etOtpUpId.getText().toString().isEmpty()) {
                    etOtpUpId.startAnimation(shakeAnimation);
                    etOtpUpId.requestFocus();
                    etOtpUpId.setError("Required");
                    etOtpUpId.getBackground().mutate().setColorFilter(ContextCompat.getColor(VerifyOtpActivity.this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                } else if (etOtpUpId.getText().toString().length() < 4) {
                    etOtpUpId.startAnimation(shakeAnimation);
                    etOtpUpId.requestFocus();
                    etOtpUpId.setError("Invalid Otp");
                    etOtpUpId.getBackground().mutate().setColorFilter(ContextCompat.getColor(VerifyOtpActivity.this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                    etOtpUpId.startAnimation(shakeAnimation);
                } else {
                    if (NetworkUtility.isNetAvailable(VerifyOtpActivity.this)) {
                        oTpVerification();
                    } else {
                        new CustomToast().Show_Toast(VerifyOtpActivity.this, v, "Check Network Connection");
                    }
                }

                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        finish();
    }

    private void oTpVerification() {
        CustomProgressbar.showProgressBar(VerifyOtpActivity.this, false);
        Log.v("data", otp + "," + name + "," + number);
        apiServices.verify_Otp(otp, name, number, token).enqueue(new Callback<OtpResponseModle>() {
            @Override
            public void onResponse(Call<OtpResponseModle> call, Response<OtpResponseModle> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    OtpResponseModle getRegistrationModle = response.body();
                    if (getRegistrationModle.getStatus()) {
                        AppSession.setStringPreferences(getApplicationContext(), Constants.USER_ID, getRegistrationModle.getData().getUserId());
                        AppSession.setStringPreferences(getApplicationContext(), Constants.USERNAME, getRegistrationModle.getData().getName());
                        AppSession.setStringPreferences(getApplicationContext(), Constants.USER_TYPE, getRegistrationModle.getData().getUserType());
                        AppSession.setStringPreferences(getApplicationContext(), Constants.MOBILE_NUMBER, getRegistrationModle.getData().getMobileNumber());
                        AppSession.setStringPreferences(getApplicationContext(), Constants.EMAIL, "" + getRegistrationModle.getData().getEmail());
                        AppSession.setStringPreferences(getApplicationContext(), Constants.TOKEN, getRegistrationModle.getData().getToken());
                        AppSession.setStringPreferences(getApplicationContext(), Constants.PROFILE_PIC, getRegistrationModle.getData().getProfilePic());
                        AppSession.setStringPreferences(getApplicationContext(), Constants.GENDER, getRegistrationModle.getData().getGender());
                        Intent intent = new Intent(VerifyOtpActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                        finish();

                    } else {
                        Toast.makeText(VerifyOtpActivity.this, "" + getRegistrationModle.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                if (response.code() == 400) {
                    CustomProgressbar.hideProgressBar();
                    if (!response.isSuccessful()) {
                        // CustomProgressbar.hideProgressBar();
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("message");
                            Toast.makeText(VerifyOtpActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<OtpResponseModle> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }

        });
    }

}
