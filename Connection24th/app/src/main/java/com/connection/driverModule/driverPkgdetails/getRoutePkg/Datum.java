
package com.connection.driverModule.driverPkgdetails.getRoutePkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
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
    @SerializedName("amount_per_km")
    @Expose
    private String amountPerKm;
    @SerializedName("total_seats")
    @Expose
    private String totalSeats;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_updated")
    @Expose
    private String dateUpdated;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param dateCreated
     * @param currentLocationLat
     * @param dropLocation
     * @param id
     * @param dropLocationLong
     * @param userId
     * @param dropLocationLat
     * @param currentLocationLong
     * @param amountPerKm
     * @param totalSeats
     * @param currentLocation
     * @param dateUpdated
     */
    public Datum(String id, String userId, String currentLocationLat, String currentLocationLong, String dropLocationLat, String dropLocationLong, String currentLocation, String dropLocation, String amountPerKm, String totalSeats, String dateCreated, String dateUpdated) {
        super();
        this.id = id;
        this.userId = userId;
        this.currentLocationLat = currentLocationLat;
        this.currentLocationLong = currentLocationLong;
        this.dropLocationLat = dropLocationLat;
        this.dropLocationLong = dropLocationLong;
        this.currentLocation = currentLocation;
        this.dropLocation = dropLocation;
        this.amountPerKm = amountPerKm;
        this.totalSeats = totalSeats;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getAmountPerKm() {
        return amountPerKm;
    }

    public void setAmountPerKm(String amountPerKm) {
        this.amountPerKm = amountPerKm;
    }

    public String getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(String totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

}
