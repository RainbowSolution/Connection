package com.connection.userModule.userProfilePkg;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.connection.ApiPkg.ApiServices;
import com.connection.ApiPkg.RetrofitClient;
import com.connection.R;
import com.connection.authenticationPkg.SplashActivity;
import com.connection.userModule.userProfilePkg.getProfileModle.GetUserProfileModle;
import com.connection.userModule.userProfilePkg.profileModle.DeleteUserProfileModle;
import com.connection.userModule.userProfilePkg.profileModle.UpdateUserProfileModle;
import com.connection.utility.AppSession;
import com.connection.utility.CheckNetwork;
import com.connection.utility.Constants;
import com.connection.utility.CustomProgressbar;
import com.connection.utility.CustomToast;
import com.connection.utility.ImagePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.connection.utility.ImagePicker.getRealPathFromURI;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout rlback, rlSaveAndExit;
    private CircleImageView ivuserprofileimage;
    private TextInputEditText etNameSignUp, etgender, etMobileNumber, etemail;
    private SwitchCompat scProfileBtn;
    private AppCompatTextView tvdeleteacc;
    private ApiServices apiServices;
    private static final int PERMISSION_READ_EXTERNAL_STORAGE = 100;
    private static final int SELECT_PICTURE = 101;
    private String profilImgPath, userId;
    private static Animation shakeAnimation;
    private File fileForImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        apiServices = RetrofitClient.getClient().create(ApiServices.class);
        userId = AppSession.getStringPreferences(UserProfileActivity.this, Constants.USER_ID);
       // Toast.makeText(getApplicationContext(), userId, Toast.LENGTH_LONG).show();
        init();
        if (CheckNetwork.isNetAvailable(getApplicationContext())) {
            getProfile();
        } else {
            // new CustomToast().Show_Toast(getApplicationContext(), v, "Check Network Connection");
        }
    }

    private void init() {
        tvdeleteacc = findViewById(R.id.tvdeleteaccId);
        scProfileBtn = findViewById(R.id.scProfileBtnId);
        rlback = findViewById(R.id.rlbackId);
        rlSaveAndExit = findViewById(R.id.rlSaveAndExitId);
        ivuserprofileimage = findViewById(R.id.ivuserprofileimageId);
        etNameSignUp = findViewById(R.id.etNameSignUpId);
        etgender = findViewById(R.id.etgenderId);
        etMobileNumber = findViewById(R.id.etMobileNumberId);
        etemail = findViewById(R.id.etemailId);

        tvdeleteacc.setOnClickListener(this);
        rlback.setOnClickListener(this);
        ivuserprofileimage.setOnClickListener(this);
        rlSaveAndExit.setOnClickListener(this);

        shakeAnimation = AnimationUtils.loadAnimation(UserProfileActivity.this, R.anim.shake);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlSaveAndExitId:
                validation(v);
                break;
            case R.id.tvdeleteaccId:
                confirmationDialoge();
                break;
            case R.id.ivuserprofileimageId:
                askStoragePermission();
                break;
            case R.id.rlbackId:
                onBackPressed();
                break;
        }

    }

    private void askStoragePermission() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            {
                ActivityCompat.requestPermissions(UserProfileActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSION_READ_EXTERNAL_STORAGE);
            }
        } else {
            chooseFromGallery();
        }
    }

    private void chooseFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PICTURE) {
            if (resultCode == RESULT_OK) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = ImagePicker.getImageFromResult(getApplicationContext(), resultCode, data);
                    ivuserprofileimage.setImageBitmap(bitmap);
                    profilImgPath = getRealPathFromURI(UserProfileActivity.this, imageUri);

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    private void validation(View v) {
        if (!etNameSignUp.getText().toString().matches("^[a-zA-Z0-9\\s]+")) {
            etNameSignUp.requestFocus();
            etNameSignUp.setError("Required");
            etNameSignUp.startAnimation(shakeAnimation);
            etNameSignUp.getBackground().mutate().setColorFilter(ContextCompat.getColor(UserProfileActivity.this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        } else if (etgender.getText().toString().isEmpty()) {
            etgender.requestFocus();
            etgender.setError("Required");
            etgender.startAnimation(shakeAnimation);
            etgender.getBackground().mutate().setColorFilter(ContextCompat.getColor(UserProfileActivity.this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        } else if (etMobileNumber.getText().toString().isEmpty()) {
            etMobileNumber.requestFocus();
            etMobileNumber.setError("Required");
            etMobileNumber.startAnimation(shakeAnimation);
            etMobileNumber.getBackground().mutate().setColorFilter(ContextCompat.getColor(UserProfileActivity.this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        } else if (etMobileNumber.getText().toString().length() > 10 || etMobileNumber.getText().toString().length() < 10) {
            etMobileNumber.requestFocus();
            etMobileNumber.setError("Invalid Number");
            etMobileNumber.startAnimation(shakeAnimation);
            etMobileNumber.getBackground().mutate().setColorFilter(ContextCompat.getColor(UserProfileActivity.this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        } else if (etemail.getText().toString().isEmpty()) {
            etemail.requestFocus();
            etemail.setError("Required");
            etemail.startAnimation(shakeAnimation);
            etemail.getBackground().mutate().setColorFilter(ContextCompat.getColor(UserProfileActivity.this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        } else if (!Constants.emailValidator(etemail.getText().toString())) {
            etemail.requestFocus();
            etemail.setError("Invalid Email Id");
            etemail.startAnimation(shakeAnimation);
            etemail.getBackground().mutate().setColorFilter(ContextCompat.getColor(UserProfileActivity.this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        } else {
            if (CheckNetwork.isNetAvailable(getApplicationContext())) {
                updateProfile();
            } else {
                new CustomToast().Show_Toast(getApplicationContext(), v, "Check Network Connection");
            }
        }
    }


    private void updateProfile() {
        CustomProgressbar.showProgressBar(UserProfileActivity.this, false);
        MultipartBody.Part imgFileStation = null;
        MultipartBody.Part imgFileStationDoc = null;
        if (profilImgPath == null) {
        } else {
            fileForImage = new File(profilImgPath);
            RequestBody requestFileOne = RequestBody.create(MediaType.parse("multipart/form-data"), fileForImage);
            imgFileStation = MultipartBody.Part.createFormData("profile_pic", fileForImage.getName(), requestFileOne);
        }
        MultipartBody.Part name_ = MultipartBody.Part.createFormData("name", String.valueOf(etNameSignUp.getText().toString()));
        MultipartBody.Part gender_ = MultipartBody.Part.createFormData("gender", String.valueOf(etgender.getText().toString()));
        MultipartBody.Part mobile_number_ = MultipartBody.Part.createFormData("mobile_number", etMobileNumber.getText().toString());
        MultipartBody.Part email_ = MultipartBody.Part.createFormData("email", etemail.getText().toString());
        MultipartBody.Part user_id_ = MultipartBody.Part.createFormData("user_id", userId);
        MultipartBody.Part show_notification_on_home_screen_ = MultipartBody.Part.createFormData("show_notification_on_home_screen", "1");

        apiServices.editUserProfile(name_, gender_, mobile_number_, email_, user_id_, show_notification_on_home_screen_,
                imgFileStation).enqueue(new Callback<UpdateUserProfileModle>() {
            @Override
            public void onResponse(Call<UpdateUserProfileModle> call, Response<UpdateUserProfileModle> response) {
                if (response.isSuccessful()) {
                    try {
                        CustomProgressbar.hideProgressBar();
                        UpdateUserProfileModle updateProfileModle = response.body();
                        if (updateProfileModle.getStatus()) {
                            Toast.makeText(getApplicationContext(), updateProfileModle.getMessage(), Toast.LENGTH_LONG).show();
                            AppSession.setStringPreferences(getApplicationContext(), Constants.USERNAME, updateProfileModle.getUserDetails().getName());
                            AppSession.setStringPreferences(getApplicationContext(), Constants.EMAIL, updateProfileModle.getUserDetails().getEmail());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    if (response.code() == 400) {
                        if (!false) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response.errorBody().string());
                                CustomProgressbar.hideProgressBar();
                                String message = jsonObject.getString("message");
                                Toast.makeText(getApplicationContext(), "" + message, Toast.LENGTH_SHORT).show();
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateUserProfileModle> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }
        });

    }


    private void deleteAccount(Dialog dialog) {
        CustomProgressbar.showProgressBar(UserProfileActivity.this, false);
        apiServices.deleteUserProfile(userId).enqueue(new Callback<DeleteUserProfileModle>() {
            @Override
            public void onResponse(Call<DeleteUserProfileModle> call, Response<DeleteUserProfileModle> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    dialog.dismiss();
                    DeleteUserProfileModle deleteUserProfileModle = response.body();
                    if (deleteUserProfileModle.getStatus()) {
                        AppSession.clearAllSharedPreferences(getApplicationContext());
                        Intent intent = new Intent(UserProfileActivity.this, SplashActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(UserProfileActivity.this, "" + deleteUserProfileModle.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            CustomProgressbar.hideProgressBar();
                            String message = jsonObject.getString("message");
                            Toast.makeText(UserProfileActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DeleteUserProfileModle> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }

        });
    }


    private void getProfile() {
        CustomProgressbar.showProgressBar(UserProfileActivity.this, false);
        apiServices.getUserProfile(userId).enqueue(new Callback<GetUserProfileModle>() {
            @Override
            public void onResponse(Call<GetUserProfileModle> call, Response<GetUserProfileModle> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    GetUserProfileModle getUserProfileModle = response.body();
                    if (getUserProfileModle.getStatus()) {
                        etNameSignUp.setText(getUserProfileModle.getData().get(0).getName());
                        etgender.setText(getUserProfileModle.getData().get(0).getGender());
                        etMobileNumber.setText(getUserProfileModle.getData().get(0).getMobileNumber());
                        etemail.setText(getUserProfileModle.getData().get(0).getEmail());

                        if (getUserProfileModle.getData().get(0).getProfilePic().isEmpty()) {
                        } else {
                            Picasso.with(getApplicationContext()).load(RetrofitClient.IMAGE_PATH + getUserProfileModle.getData().get(0).getProfilePic())
                                    .resize(200, 200)
                                    .into(ivuserprofileimage);
                        }

                    } else {
                        Toast.makeText(UserProfileActivity.this, "" + getUserProfileModle.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            CustomProgressbar.hideProgressBar();
                            String message = jsonObject.getString("message");
                            Toast.makeText(UserProfileActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetUserProfileModle> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        finish();
    }


    public void confirmationDialoge() {
        final Dialog dialog = new Dialog(UserProfileActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.confirmatin_dialoge);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        AppCompatImageView ivDeleteClose = dialog.findViewById(R.id.ivDeleteCloseId);
        RelativeLayout rlDelete = dialog.findViewById(R.id.rlDeleteId);
        ivDeleteClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        rlDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAccount(dialog);
            }
        });
        dialog.show();

    }


    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

}
