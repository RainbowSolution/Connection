
package com.connection.userModule.userProfilePkg.profileModle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateUserProfileModle {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user_details")
    @Expose
    private UserDetails userDetails;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UpdateUserProfileModle() {
    }

    /**
     * 
     * @param message
     * @param userDetails
     * @param status
     */
    public UpdateUserProfileModle(Boolean status, String message, UserDetails userDetails) {
        super();
        this.status = status;
        this.message = message;
        this.userDetails = userDetails;
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

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

}
