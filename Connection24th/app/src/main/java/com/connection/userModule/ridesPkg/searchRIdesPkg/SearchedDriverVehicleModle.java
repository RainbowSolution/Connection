
package com.connection.userModule.ridesPkg.searchRIdesPkg;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchedDriverVehicleModle {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("searchDriver")
    @Expose
    private List<SearchDriver> searchDriver = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SearchedDriverVehicleModle() {
    }

    /**
     * 
     * @param searchDriver
     * @param status
     */
    public SearchedDriverVehicleModle(Boolean status, List<SearchDriver> searchDriver) {
        super();
        this.status = status;
        this.searchDriver = searchDriver;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<SearchDriver> getSearchDriver() {
        return searchDriver;
    }

    public void setSearchDriver(List<SearchDriver> searchDriver) {
        this.searchDriver = searchDriver;
    }

}
