package com.connection.driverModule.driverPkgdetails;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.connection.ApiPkg.ApiServices;
import com.connection.ApiPkg.RetrofitClient;
import com.connection.R;
import com.connection.driverModule.driverPkgdetails.profileModlePkg.GetDriverProfileModle;
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

import static android.app.Activity.RESULT_OK;
import static com.connection.utility.ImagePicker.getRealPathFromURI;

public class DriverRegistrationFragment extends Fragment implements View.OnClickListener {
    AppCompatImageView ivbackId;
    RelativeLayout rlsubmitId;
    private ApiServices apiServices;
    private TextInputEditText etfullNameS, etVehicleName,
            etVehicleNumber, etDriverLicence;
    private AppCompatImageView ivVehiclePhoto,
            ivPoliceCertification, ivPermitPhoto, ivRc;
    private String userId;

    private static Animation shakeAnimation;
    private CircleImageView ivuserprofileimage;

    private static final int PERMISSION_READ_EXTERNAL_STORAGE = 100;
    private static final int SELECT_PICTURE = 101;
    private String profilImgPath, vehiclePhotoPath, policeVerificationPath, permitPath, registrationCertiPath;
    private File fileProfile, fileVehiclePhoto, filePoliceVerificationCert, filePermit, fileRegistrationCert;
    private String imgFlag;

    private String userStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.driver_registration_layout, container, false);
        apiServices = RetrofitClient.getClient().create(ApiServices.class);
        userId = AppSession.getStringPreferences(getActivity(), Constants.USER_ID);
       // Toast.makeText(getActivity(), userId, Toast.LENGTH_LONG).show();
        init(view);
        if (CheckNetwork.isNetAvailable(getActivity())) {
            //getDriverProfile();
        } else {
            Toast.makeText(getActivity(), "Check Network Connection", Toast.LENGTH_LONG).show();
        }
        return view;
    }

    private void init(View view) {
        ivuserprofileimage = view.findViewById(R.id.ivuserprofileimageId);
        ivbackId = view.findViewById(R.id.ivbackId);
        rlsubmitId = view.findViewById(R.id.rlsubmitId);
        etfullNameS = view.findViewById(R.id.etfullNameSId);
        etVehicleName = view.findViewById(R.id.etVehicleNameId);
        etVehicleNumber = view.findViewById(R.id.etVehicleNumberId);
        etDriverLicence = view.findViewById(R.id.etDriverLicenceId);

        ivVehiclePhoto = view.findViewById(R.id.ivVehiclePhotoId);
        ivPoliceCertification = view.findViewById(R.id.ivPoliceCertificationId);
        ivPermitPhoto = view.findViewById(R.id.ivPermitPhotoId);
        ivRc = view.findViewById(R.id.ivRcId);

        ivbackId.setOnClickListener(this);
        rlsubmitId.setOnClickListener(this);
        ivVehiclePhoto.setOnClickListener(this);
        ivPoliceCertification.setOnClickListener(this);
        ivPermitPhoto.setOnClickListener(this);
        ivRc.setOnClickListener(this);
        ivuserprofileimage.setOnClickListener(this);

        shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivuserprofileimageId:
                imgFlag = "0";
                askStoragePermission();
                break;
            case R.id.ivVehiclePhotoId:
                imgFlag = "1";
                askStoragePermission();
                break;
            case R.id.ivPoliceCertificationId:
                imgFlag = "2";
                askStoragePermission();
                break;
            case R.id.ivPermitPhotoId:
                imgFlag = "3";
                askStoragePermission();
                break;
            case R.id.ivRcId:
                imgFlag = "4";
                askStoragePermission();
                break;
            case R.id.ivbackId:
                break;
            case R.id.rlsubmitId:
               /* if (userStatus.equalsIgnoreCase("0")) {
                    validation(v);
                } else {
                    validation(v);
                    //   addFragment(new StartSharingFragment());
                }*/
                addFragment(new DriverRefreshFragment());
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


    private void getDriverProfile() {
        CustomProgressbar.showProgressBar(getActivity(), false);
        apiServices.get_driver_profile(userId).enqueue(new Callback<GetDriverProfileModle>() {
            @Override
            public void onResponse(Call<GetDriverProfileModle> call, Response<GetDriverProfileModle> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    GetDriverProfileModle getDriverProfileModle = response.body();
                    if (getDriverProfileModle.getStatus()) {
                        etfullNameS.setText(getDriverProfileModle.getData().get(0).getName());
                        etVehicleName.setText(getDriverProfileModle.getData().get(0).getVehicleName());
                        etVehicleNumber.setText(getDriverProfileModle.getData().get(0).getVehicleNumber());
                        etDriverLicence.setText(getDriverProfileModle.getData().get(0).getDriverLicenseNumber());
                        userStatus = getDriverProfileModle.getData().get(0).getDriverVerificationStatus();

                        if (getDriverProfileModle.getData().get(0).getProfile_pic().isEmpty()) {

                        } else {
                            Picasso.with(getActivity()).load(RetrofitClient.IMAGE_PATH + getDriverProfileModle.getData().get(0).getProfile_pic())
                                    .resize(200, 200)
                                    .into(ivuserprofileimage);
                        }


                        if (getDriverProfileModle.getData().get(0).getVehiclePhoto().isEmpty()) {
                        } else {
                            Picasso.with(getActivity()).load(RetrofitClient.IMAGE_PATH + getDriverProfileModle.getData().get(0).getVehiclePhoto())
                                    .resize(200, 200)
                                    .into(ivVehiclePhoto);
                        }

                        if (getDriverProfileModle.getData().get(0).getPoliceVerificationCertificate().isEmpty()) {
                        } else {
                            Picasso.with(getActivity()).load(RetrofitClient.IMAGE_PATH + getDriverProfileModle.getData().get(0).getPoliceVerificationCertificate())
                                    .resize(200, 200)
                                    .into(ivPoliceCertification);
                        }
                        if (getDriverProfileModle.getData().get(0).getRegistrationCertificate().isEmpty()) {
                        } else {
                            Picasso.with(getActivity()).load(RetrofitClient.IMAGE_PATH + getDriverProfileModle.getData().get(0).getRegistrationCertificate())
                                    .resize(200, 200)
                                    .into(ivRc);
                        }
                        if (getDriverProfileModle.getData().get(0).getPermit().isEmpty()) {
                        } else {
                            Picasso.with(getActivity()).load(RetrofitClient.IMAGE_PATH + getDriverProfileModle.getData().get(0).getPermit())
                                    .resize(200, 200)
                                    .into(ivPermitPhoto);
                        }

                    } else {
                        Toast.makeText(getActivity(), "" + getDriverProfileModle.getMessage(), Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<GetDriverProfileModle> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }

        });
    }


    private void validation(View v) {
        if (!etfullNameS.getText().toString().matches("^[a-zA-Z0-9\\s]+")) {
            etfullNameS.requestFocus();
            etfullNameS.setError("Required");
            etfullNameS.startAnimation(shakeAnimation);
            etfullNameS.getBackground().mutate().setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        } else if (etVehicleName.getText().toString().isEmpty()) {
            etVehicleName.requestFocus();
            etVehicleName.setError("Required");
            etVehicleName.startAnimation(shakeAnimation);
            etVehicleName.getBackground().mutate().setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        } else if (etVehicleNumber.getText().toString().isEmpty()) {
            etVehicleNumber.requestFocus();
            etVehicleNumber.setError("Required");
            etVehicleNumber.startAnimation(shakeAnimation);
            etVehicleNumber.getBackground().mutate().setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        } else if (!Constants.vehicleNumberValidator(etVehicleNumber.getText().toString())) {
            etVehicleNumber.requestFocus();
            etVehicleNumber.setError("Invalid Vehicle Number");
            etVehicleNumber.startAnimation(shakeAnimation);
            etVehicleNumber.getBackground().mutate().setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        } else if (etDriverLicence.getText().toString().isEmpty()) {
            etDriverLicence.requestFocus();
            etDriverLicence.setError("Required");
            etDriverLicence.startAnimation(shakeAnimation);
            etDriverLicence.getBackground().mutate().setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        } else if (!Constants.vehicleLicense(etDriverLicence.getText().toString())) {
            etDriverLicence.requestFocus();
            etDriverLicence.setError("Invalid License");
            etDriverLicence.startAnimation(shakeAnimation);
            etDriverLicence.getBackground().mutate().setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        }
        //vehicleLicense
        else {
            if (CheckNetwork.isNetAvailable(getActivity())) {
                updateRegisterDriverProfile();
            } else {
                new CustomToast().Show_Toast(getActivity(), v, "Check Network Connection");
            }
        }
    }


    private void updateRegisterDriverProfile() {
        CustomProgressbar.showProgressBar(getActivity(), false);
        MultipartBody.Part imgFileStationProfilePic = null;
        MultipartBody.Part imgFileStationVehiclePhoto = null;
        MultipartBody.Part imgFileStationPoliceVerfication = null;
        MultipartBody.Part imgFileStationRegist_Certicate = null;
        MultipartBody.Part imgFileStationPermit = null;

        //fileProfile

        if (profilImgPath == null) {
        } else {
            fileProfile = new File(profilImgPath);
            RequestBody requestFileOne = RequestBody.create(MediaType.parse("multipart/form-data"), fileProfile);
            imgFileStationProfilePic = MultipartBody.Part.createFormData("profile_pic", fileProfile.getName(), requestFileOne);
        }

        if (vehiclePhotoPath == null) {
        } else {
            fileVehiclePhoto = new File(vehiclePhotoPath);
            RequestBody requestFileOne = RequestBody.create(MediaType.parse("multipart/form-data"), fileVehiclePhoto);
            imgFileStationProfilePic = MultipartBody.Part.createFormData("vehicle_photo", fileVehiclePhoto.getName(), requestFileOne);
        }

        if (policeVerificationPath == null) {
        } else {
            filePoliceVerificationCert = new File(policeVerificationPath);
            RequestBody requestFileOne = RequestBody.create(MediaType.parse("multipart/form-data"), filePoliceVerificationCert);
            imgFileStationVehiclePhoto = MultipartBody.Part.createFormData("police_verification_certificate", filePoliceVerificationCert.getName(), requestFileOne);
        }

        if (registrationCertiPath == null) {
        } else {
            fileRegistrationCert = new File(registrationCertiPath);
            RequestBody requestFileOne = RequestBody.create(MediaType.parse("multipart/form-data"), fileRegistrationCert);
            imgFileStationPoliceVerfication = MultipartBody.Part.createFormData("registration_certificate", fileRegistrationCert.getName(), requestFileOne);
        }

        if (permitPath == null) {
        } else {
            filePermit = new File(permitPath);
            RequestBody requestFileOne = RequestBody.create(MediaType.parse("multipart/form-data"), filePermit);
            imgFileStationPermit = MultipartBody.Part.createFormData("permit", filePermit.getName(), requestFileOne);
        }


        MultipartBody.Part name_ = MultipartBody.Part.createFormData("name", String.valueOf(etfullNameS.getText().toString()));
        MultipartBody.Part vehicle_name_ = MultipartBody.Part.createFormData("vehicle_name", String.valueOf(etVehicleName.getText().toString()));
        MultipartBody.Part vehicle_number_ = MultipartBody.Part.createFormData("vehicle_number", etVehicleNumber.getText().toString());
        MultipartBody.Part driver_license_number_ = MultipartBody.Part.createFormData("driver_license_number", etDriverLicence.getText().toString());
        MultipartBody.Part user_id_ = MultipartBody.Part.createFormData("user_id", userId);

        apiServices.submitDriverDetails(name_, vehicle_name_, vehicle_number_,
                driver_license_number_, user_id_, imgFileStationProfilePic,
                imgFileStationVehiclePhoto, imgFileStationPoliceVerfication,
                imgFileStationPermit, imgFileStationRegist_Certicate).enqueue(new Callback<UpdateUserProfileModle>() {
            @Override
            public void onResponse(Call<UpdateUserProfileModle> call, Response<UpdateUserProfileModle> response) {
                if (response.isSuccessful()) {
                    try {
                        CustomProgressbar.hideProgressBar();
                        UpdateUserProfileModle updateProfileModle = response.body();
                        if (updateProfileModle.getStatus()) {
                            Toast.makeText(getActivity(), updateProfileModle.getMessage(), Toast.LENGTH_LONG).show();
                            addFragment(new DriverRefreshFragment());
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
                                Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();
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


    private void askStoragePermission() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            {
                ActivityCompat.requestPermissions(getActivity(),
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
                    Bitmap bitmap = ImagePicker.getImageFromResult(getActivity(), resultCode, data);
                    if (imgFlag.equalsIgnoreCase("0")) {
                        ivuserprofileimage.setImageBitmap(bitmap);
                        profilImgPath = getRealPathFromURI(getActivity(), imageUri);
                    } else if (imgFlag.equalsIgnoreCase("1")) {
                        ivVehiclePhoto.setImageBitmap(bitmap);
                        vehiclePhotoPath = getRealPathFromURI(getActivity(), imageUri);
                    } else if (imgFlag.equalsIgnoreCase("2")) {
                        ivPoliceCertification.setImageBitmap(bitmap);
                        policeVerificationPath = getRealPathFromURI(getActivity(), imageUri);
                    } else if (imgFlag.equalsIgnoreCase("3")) {
                        ivPermitPhoto.setImageBitmap(bitmap);
                        permitPath = getRealPathFromURI(getActivity(), imageUri);
                    } else if (imgFlag.equalsIgnoreCase("4")) {
                        ivRc.setImageBitmap(bitmap);
                        registrationCertiPath = getRealPathFromURI(getActivity(), imageUri);
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
