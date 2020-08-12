package com.connection.authenticationPkg;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.connection.ApiPkg.ApiServices;
import com.connection.ApiPkg.RetrofitClient;
import com.connection.MainActivity;
import com.connection.R;
import com.connection.authenticationPkg.authenticationModle.signInModlePkg.LogineResponseModle;
import com.connection.authenticationPkg.authenticationModle.socialLoginPkg.SocialApiResponseModle;
import com.connection.utility.AppSession;
import com.connection.utility.CheckNetwork;
import com.connection.utility.Constants;
import com.connection.utility.CustomProgressbar;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends BasicActivity implements View.OnClickListener {
    private static Animation shakeAnimation;
    private RelativeLayout rlSignin;
    private TextView TvSignup;
    private TextInputEditText etuserNameId, etNumber;
    private AppCompatCheckBox cbtermconditionid;
    private ApiServices apiServices;
    private AppCompatImageView ivGoogleLoginId, ivFacebookLoginId;
    private GoogleSignInClient googleSignInClient;
    private CallbackManager callbackManager;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        apiServices = RetrofitClient.getClient().create(ApiServices.class);
        callbackManager = CallbackManager.Factory.create();
        init();
    }

    private void init() {
        rlSignin = findViewById(R.id.rlSigninId);
        rlSignin.setOnClickListener(this);
        TvSignup = findViewById(R.id.TvSignupId);
        TvSignup.setOnClickListener(this);
        etuserNameId = findViewById(R.id.etuserNameId);
        etNumber = findViewById(R.id.etNumberId);
        cbtermconditionid = findViewById(R.id.cbtermconditionid);
        shakeAnimation = AnimationUtils.loadAnimation(SignInActivity.this, R.anim.shake);
        ivGoogleLoginId = findViewById(R.id.ivGoogleLoginId);
        ivFacebookLoginId = findViewById(R.id.ivFacebookLoginId);
        ivGoogleLoginId.setOnClickListener(this);
        ivFacebookLoginId.setOnClickListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                // .requestIdToken(getResources().getString(R.string.web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        boolean loggedOut = AccessToken.getCurrentAccessToken() == null;
        if (!loggedOut) {
            //  getUserProfile(AccessToken.getCurrentAccessToken());
        }

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                getUserProfile(accessToken);
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
            }
        });

        FirebaseApp.initializeApp(SignInActivity.this);
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
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlSigninId:
                if (etuserNameId.getText().toString().isEmpty()) {
                    etuserNameId.requestFocus();
                    etuserNameId.setError("Required ");
                    etuserNameId.startAnimation(shakeAnimation);
                    etuserNameId.getBackground().mutate().setColorFilter(ContextCompat.getColor(SignInActivity.this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                } else if (!etuserNameId.getText().toString().matches("^[a-zA-Z0-9\\s]+")) {
                    etuserNameId.requestFocus();
                    etuserNameId.setError("Invalid Name");
                    etuserNameId.startAnimation(shakeAnimation);
                    etuserNameId.getBackground().mutate().setColorFilter(ContextCompat.getColor(SignInActivity.this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                } else if (etNumber.getText().toString().isEmpty()) {
                    etNumber.requestFocus();
                    etNumber.setError("Required");
                    etNumber.startAnimation(shakeAnimation);
                    etNumber.getBackground().mutate().setColorFilter(ContextCompat.getColor(SignInActivity.this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                } else if (etNumber.getText().toString().length() > 10 || etNumber.getText().toString().length() < 10) {
                    etNumber.requestFocus();
                    etNumber.setError("Invalid Number");
                    etNumber.startAnimation(shakeAnimation);
                    etNumber.getBackground().mutate().setColorFilter(ContextCompat.getColor(SignInActivity.this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                } else {
                    if (CheckNetwork.isNetAvailable(getApplicationContext())) {
                        signIn(etuserNameId.getText().toString(), etNumber.getText().toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "Check Network Connection", Toast.LENGTH_LONG).show();
                    }
                }

                break;

            case R.id.ivGoogleLoginId:
                GoogleSignInOptions gso = new GoogleSignInOptions.
                        Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                        build();
                GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(getApplicationContext(), gso);
                googleSignInClient.signOut();

                GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
                if (alreadyloggedAccount != null) {
                    onLoggedIn(alreadyloggedAccount);
                } else {
                    Intent signInIntent = googleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, 101);
                    // sociallogin1("l","j","Facebook");
                }

                break;
            case R.id.ivFacebookLoginId:
                LoginManager.getInstance().logOut();
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("user_photos", "email", "public_profile", "user_posts"));
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            if (requestCode == 101) {
                try {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    onLoggedIn(account);
                } catch (ApiException e) {
                    // Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
                }
            } else {
                callbackManager.onActivityResult(requestCode, resultCode, data);

            }
    }

    private void onLoggedIn(GoogleSignInAccount googleSignInAccount) {
        String name = googleSignInAccount.getDisplayName();
        String email = googleSignInAccount.getEmail();
        sociallogin1(name, "2", email);
    }

    private void getUserProfile(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("TAG", object.toString());
                        try {
                            String first_name = object.getString("first_name");
                            String last_name = object.getString("last_name");
                            String id = object.getString("id");
                            sociallogin1(first_name + last_name, "1", id);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }

    private void signIn(String userName, String number) {
        CustomProgressbar.showProgressBar(SignInActivity.this, false);
        apiServices.login(userName, number, token).enqueue(new Callback<LogineResponseModle>() {
            @Override
            public void onResponse(Call<LogineResponseModle> call, Response<LogineResponseModle> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    LogineResponseModle getRegistrationModle = response.body();
                    if (getRegistrationModle.getStatus()) {
                        AppSession.setStringPreferences(getApplicationContext(), Constants.STATUS, "1");
                        Intent intent = new Intent(SignInActivity.this, VerifyOtpActivity.class);
                        intent.putExtra("otp", "" + getRegistrationModle.getData().getOtp());
                        intent.putExtra("number", getRegistrationModle.getData().getMobileNumber());
                        intent.putExtra("token", getRegistrationModle.getData().getToken());
                        intent.putExtra("name", getRegistrationModle.getData().getName());
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                       // finish();
                    } else {
                        Toast.makeText(SignInActivity.this, "" + getRegistrationModle.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            CustomProgressbar.hideProgressBar();
                            String message = jsonObject.getString("message");
                            Toast.makeText(SignInActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<LogineResponseModle> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }

        });
    }

    private void sociallogin1(String name, String status, String emailId) {
        CustomProgressbar.showProgressBar(SignInActivity.this, false);
        apiServices.social_login(name, status, emailId, token).enqueue(new Callback<SocialApiResponseModle>() {
            @Override
            public void onResponse(Call<SocialApiResponseModle> call, Response<SocialApiResponseModle> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    SocialApiResponseModle getLoginModle = response.body();
                    if (getLoginModle.getStatus()) {
                        AppSession.setStringPreferences(getApplicationContext(), Constants.USER_ID, getLoginModle.getData().getUserId());
                        AppSession.setStringPreferences(getApplicationContext(), Constants.USERNAME, getLoginModle.getData().getName());
                        AppSession.setStringPreferences(getApplicationContext(), Constants.STATUS, status);
                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                        finish();
                    }
                } else {
                    if (response.code() == 400) {
                        if (!false) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response.errorBody().string());
                                CustomProgressbar.hideProgressBar();
                                String message = jsonObject.getString("message");
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SocialApiResponseModle> call, Throwable t) {
                Log.d("test", String.valueOf(t));
                CustomProgressbar.hideProgressBar();
            }
        });
    }
}
