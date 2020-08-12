
package com.connection.authenticationPkg.authenticationModle.otpModlePkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("token")
    @Expose
    private String token;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param gender
     * @param mobileNumber
     * @param profilePic
     * @param name
     * @param userType
     * @param userId
     * @param email
     * @param token
     */
    public Data(String userId, String userType, String profilePic, String email, String gender, String name, String mobileNumber, String token) {
        super();
        this.userId = userId;
        this.userType = userType;
        this.profilePic = profilePic;
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
