package com.connection.authenticationPkg.authenticationModle.signInModlePkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("otp")
    @Expose
    private Integer otp;
    @SerializedName("token")
    @Expose
    private String token;

    /**
     * No args constructor for use in serialization
     */
    public Data() {
    }

    /**
     * @param mobileNumber
     * @param name
     * @param otp
     * @param token
     */
    public Data(String name, String mobileNumber, Integer otp, String token) {
        super();
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.otp = otp;
        this.token = token;
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

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
