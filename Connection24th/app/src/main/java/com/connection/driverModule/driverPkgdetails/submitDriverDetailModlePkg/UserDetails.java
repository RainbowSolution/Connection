
package com.connection.driverModule.driverPkgdetails.submitDriverDetailModlePkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetails {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("vehicle_name")
    @Expose
    private String vehicleName;
    @SerializedName("vehicle_number")
    @Expose
    private String vehicleNumber;
    @SerializedName("driver_license_number")
    @Expose
    private String driverLicenseNumber;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserDetails() {
    }

    /**
     * 
     * @param vehicleName
     * @param driverLicenseNumber
     * @param name
     * @param vehicleNumber
     * @param id
     */
    public UserDetails(String id, String name, String vehicleName, String vehicleNumber, String driverLicenseNumber) {
        super();
        this.id = id;
        this.name = name;
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

}
