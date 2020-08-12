
package com.connection.userModule.ridesPkg.searchRIdesPkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchDriver {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("driver_id")
    @Expose
    private String driver_id;

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("vehicle_name")
    @Expose
    private String vehicleName;
    @SerializedName("vehicle_number")
    @Expose
    private String vehicleNumber;
    @SerializedName("vehicle_photo")
    @Expose
    private String vehiclePhoto;
    @SerializedName("current_location_lat")
    @Expose
    private String currentLocationLat;
    @SerializedName("current_location_long")
    @Expose
    private String currentLocationLong;
    @SerializedName("drop_location_lat")
    @Expose
    private String dropLocationLat;
    @SerializedName("drop_location_long")
    @Expose
    private String dropLocationLong;
    @SerializedName("current_location")
    @Expose
    private String currentLocation;
    @SerializedName("drop_location")
    @Expose
    private String dropLocation;

    /**
     * No args constructor for use in serialization
     */
    public SearchDriver() {
    }

    /**
     * @param vehicleName
     * @param gender
     * @param city
     * @param mobileNumber
     * @param currentLocationLat
     * @param dropLocation
     * @param vehiclePhoto
     * @param dropLocationLat
     * @param currentLocation
     * @param name
     * @param vehicleNumber
     * @param id
     * @param dropLocationLong
     * @param email
     * @param currentLocationLong
     */
    public SearchDriver(String id, String name, String email, String city, String mobileNumber, String gender, String vehicleName, String vehicleNumber, String vehiclePhoto, String currentLocationLat, String currentLocationLong, String dropLocationLat, String dropLocationLong, String currentLocation, String dropLocation) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
        this.vehiclePhoto = vehiclePhoto;
        this.currentLocationLat = currentLocationLat;
        this.currentLocationLong = currentLocationLong;
        this.dropLocationLat = dropLocationLat;
        this.dropLocationLong = dropLocationLong;
        this.currentLocation = currentLocation;
        this.dropLocation = dropLocation;
    }


    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getVehiclePhoto() {
        return vehiclePhoto;
    }

    public void setVehiclePhoto(String vehiclePhoto) {
        this.vehiclePhoto = vehiclePhoto;
    }

    public String getCurrentLocationLat() {
        return currentLocationLat;
    }

    public void setCurrentLocationLat(String currentLocationLat) {
        this.currentLocationLat = currentLocationLat;
    }

    public String getCurrentLocationLong() {
        return currentLocationLong;
    }

    public void setCurrentLocationLong(String currentLocationLong) {
        this.currentLocationLong = currentLocationLong;
    }

    public String getDropLocationLat() {
        return dropLocationLat;
    }

    public void setDropLocationLat(String dropLocationLat) {
        this.dropLocationLat = dropLocationLat;
    }

    public String getDropLocationLong() {
        return dropLocationLong;
    }

    public void setDropLocationLong(String dropLocationLong) {
        this.dropLocationLong = dropLocationLong;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }

}
