
package com.connection.driverModule.riderequestPkg.requstListModlePkg;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRequestListModle {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("userRequest")
    @Expose
    private List<UserRequest> userRequest = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserRequestListModle() {
    }

    /**
     * 
     * @param userRequest
     * @param status
     */
    public UserRequestListModle(Boolean status, List<UserRequest> userRequest) {
        super();
        this.status = status;
        this.userRequest = userRequest;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<UserRequest> getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(List<UserRequest> userRequest) {
        this.userRequest = userRequest;
    }

}
