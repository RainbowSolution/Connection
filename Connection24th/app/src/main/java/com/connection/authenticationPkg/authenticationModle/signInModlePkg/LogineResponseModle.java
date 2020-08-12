package com.connection.authenticationPkg.authenticationModle.signInModlePkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogineResponseModle {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    /**
     * No args constructor for use in serialization
     */
    public LogineResponseModle() {
    }

    /**
     * @param data
     * @param message
     * @param status
     */
    public LogineResponseModle(boolean status, String message, Data data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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
