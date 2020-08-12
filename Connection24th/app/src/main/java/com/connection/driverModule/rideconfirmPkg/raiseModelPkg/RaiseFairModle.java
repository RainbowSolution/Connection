
package com.connection.driverModule.rideconfirmPkg.raiseModelPkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RaiseFairModle {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("raiseFair")
    @Expose
    private RaiseFair raiseFair;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RaiseFairModle() {
    }

    /**
     * 
     * @param raiseFair
     * @param status
     */
    public RaiseFairModle(Boolean status, RaiseFair raiseFair) {
        super();
        this.status = status;
        this.raiseFair = raiseFair;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public RaiseFair getRaiseFair() {
        return raiseFair;
    }

    public void setRaiseFair(RaiseFair raiseFair) {
        this.raiseFair = raiseFair;
    }

}
