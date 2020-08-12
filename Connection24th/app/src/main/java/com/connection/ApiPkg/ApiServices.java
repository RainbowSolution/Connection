package com.connection.ApiPkg;

import com.connection.authenticationPkg.authenticationModle.otpModlePkg.OtpResponseModle;
import com.connection.authenticationPkg.authenticationModle.signInModlePkg.LogineResponseModle;
import com.connection.authenticationPkg.authenticationModle.socialLoginPkg.SocialApiResponseModle;
import com.connection.driverModule.driverPkgdetails.DriverCreateRouteModlePkg.DriverCreateRouteModle;
import com.connection.driverModule.driverPkgdetails.driverRegreshModlePkg.DriverVerificationModle;
import com.connection.driverModule.driverPkgdetails.getRoutePkg.GetRouteDetailModle;
import com.connection.driverModule.driverPkgdetails.profileModlePkg.GetDriverProfileModle;
import com.connection.driverModule.rideconfirmPkg.raiseModelPkg.RaiseFairModle;
import com.connection.driverModule.riderequestPkg.requstListModlePkg.UserRequestListModle;
import com.connection.userModule.ModelClass.getCategoryPkg.CategoryListModle;
import com.connection.userModule.accommodationPkg.accomodationModle.GetAccommodationModel;
import com.connection.userModule.ridesPkg.searchRIdesPkg.SearchedDriverVehicleModle;
import com.connection.userModule.ridesPkg.searchRIdesPkg.acceptDriverModlePkg.RideAcceptModle;
import com.connection.userModule.userProfilePkg.getProfileModle.GetUserProfileModle;
import com.connection.userModule.userProfilePkg.profileModle.DeleteUserProfileModle;
import com.connection.userModule.userProfilePkg.profileModle.UpdateUserProfileModle;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiServices {


    @FormUrlEncoded
    @POST("Authentication/login")
    Call<LogineResponseModle> login(@Field("name") String username,
                                    @Field("mobile_number") String password,
                                    @Field("token") String firebaseToken);


    @FormUrlEncoded
    @POST("Authentication/user_request_to_driver_list")
    Call<UserRequestListModle> user_request_to_driver_list(@Field("driver_id") String driver_id);

    @FormUrlEncoded
    @POST("Authentication/delete_user_profile")
    Call<DeleteUserProfileModle> deleteUserProfile(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Authentication/get_user_profile")
    Call<GetUserProfileModle> getUserProfile(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("Authentication/get_driver_profile")
    Call<GetDriverProfileModle> get_driver_profile(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("Authentication/searchDriverByLocation")
    Call<SearchedDriverVehicleModle> searchDriverByLocation(
            @Field("current_location_lat") String current_location_lat,
            @Field("current_location_long") String current_location_long,
            @Field("current_location") String current_location,
            @Field("drop_location") String drop_location
    );


    @FormUrlEncoded
    @POST("Authentication/user_accept_ride")
    Call<RideAcceptModle> user_accept_ride(
            @Field("user_id") String user_id,
            @Field("driver_id") String driver_id,
            @Field("driver_route_id") String driver_route_id
    );


    @FormUrlEncoded
    @POST("Authentication/get_driver_verification_status")
    Call<DriverVerificationModle> get_driver_verification_status(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("Authentication/crete_a_route")
    Call<DriverCreateRouteModle> crete_a_route(
            @Field("user_id") String user_id,
            @Field("current_location_lat") String current_location_lat,
            @Field("current_location_long") String current_location_long,
            @Field("drop_location_lat") String drop_location_lat,
            @Field("drop_location_long") String drop_location_long,
            @Field("current_location") String current_location,
            @Field("drop_location") String drop_location,
            @Field("amount_per_km") String amount_per_km,
            @Field("total_seats") String total_seats);


    @FormUrlEncoded
    @POST("Authentication/get_route")
    Call<GetRouteDetailModle> get_route(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("Authentication/raiseFair")
    Call<RaiseFairModle> raiseFair(
            @Field("user_id") String user_id,
            @Field("driver_id") String driver_id,
            @Field("fair_amount") String fair_amount
    );

    @FormUrlEncoded
    @POST("Authentication/otpVarification")
    Call<OtpResponseModle> verify_Otp(@Field("otp") String otp,
                                      @Field("name") String name,
                                      @Field("mobile_number") String mobile_number,
                                      @Field("token") String token);


    @FormUrlEncoded
    @POST("Authentication/socialLogin")
    Call<SocialApiResponseModle> social_login(@Field("name") String name,
                                              @Field("status") String status,
                                              @Field("email") String email,
                                              @Field("token") String token);


    @GET("Authentication/getCategoryList")
    Call<CategoryListModle> getCategoryList();

    @FormUrlEncoded
    @POST("Authentication/getProductByCategoryId")
    Call<GetAccommodationModel> getaccommodationList(@Field("category_id") String category_id);


    @Multipart
    @POST("Authentication/editProfile")
    Call<UpdateUserProfileModle> editUserProfile(@Part MultipartBody.Part name,
                                                 @Part MultipartBody.Part gender,
                                                 @Part MultipartBody.Part mobile_number,
                                                 @Part MultipartBody.Part email,
                                                 @Part MultipartBody.Part user_id,
                                                 @Part MultipartBody.Part show_notification_on_home_screen,
                                                 @Part MultipartBody.Part profile_pic);

    @Multipart
    @POST("Authentication/submitDriverDetails")
    Call<UpdateUserProfileModle> submitDriverDetails(@Part MultipartBody.Part name,
                                                     @Part MultipartBody.Part vehicle_name,
                                                     @Part MultipartBody.Part vehicle_number,
                                                     @Part MultipartBody.Part driver_license_number,
                                                     @Part MultipartBody.Part user_id,
                                                     @Part MultipartBody.Part profile_pic,
                                                     @Part MultipartBody.Part vehicle_photo,
                                                     @Part MultipartBody.Part police_verification_certificate,
                                                     @Part MultipartBody.Part permit,
                                                     @Part MultipartBody.Part registration_certificate);


}
