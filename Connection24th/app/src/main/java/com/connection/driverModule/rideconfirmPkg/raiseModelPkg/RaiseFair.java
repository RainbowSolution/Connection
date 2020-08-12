
package com.connection.driverModule.rideconfirmPkg.raiseModelPkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RaiseFair {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("driver_id")
    @Expose
    private String driverId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("fair_amount")
    @Expose
    private String fairAmount;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RaiseFair() {
    }

    /**
     * 
     * @param driverId
     * @param id
     * @param userId
     * @param fairAmount
     */
    public RaiseFair(Integer id, String driverId, String userId, String fairAmount) {
        super();
        this.id = id;
        this.driverId = driverId;
        this.userId = userId;
        this.fairAmount = fairAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFairAmount() {
        return fairAmount;
    }

    public void setFairAmount(String fairAmount) {
        this.fairAmount = fairAmount;
    }

}
