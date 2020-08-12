package com.connection.authenticationPkg.authenticationModle.socialLoginPkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialApiResponseModle {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    /**
     * No args constructor for use in serialization
     */
    public SocialApiResponseModle() {
    }

    /**
     * @param data
     * @param message
     * @param status
     */
    public SocialApiResponseModle(Boolean status, String message, Data data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
