
package com.connection.driverModule.riderequestPkg.requstListModlePkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRequest {

    @SerializedName("driver_id")
    @Expose
    private String driverId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("source_location")
    @Expose
    private String sourceLocation;
    @SerializedName("destination_location")
    @Expose
    private String destinationLocation;
    @SerializedName("request_date")
    @Expose
    private String requestDate;
    @SerializedName("request_time")
    @Expose
    private String requestTime;
    @SerializedName("distance_in_km")
    @Expose
    private String distanceInKm;


    @SerializedName("created")
    @Expose
    private String created;


    /**
     * No args constructor for use in serialization
     * 
     */
    public UserRequest() {
    }

    /**
     * 
     * @param gender
     * @param mobileNumber
     * @param profilePic
     * @param destinationLocation
     * @param userId
     * @param distanceInKm
     * @param requestTime
     * @param driverId
     * @param name
     * @param requestDate
     * @param location
     * @param sourceLocation
     * @param userType
     * @param email
     */
    public UserRequest(String driverId, String userId, String userType, String profilePic, String email, String gender, String name, String mobileNumber, String location, String sourceLocation, String destinationLocation, String requestDate, String requestTime, String distanceInKm) {
        super();
        this.driverId = driverId;
        this.userId = userId;
        this.userType = userType;
        this.profilePic = profilePic;
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.location = location;
        this.sourceLocation = sourceLocation;
        this.destinationLocation = destinationLocation;
        this.requestDate = requestDate;
        this.requestTime = requestTime;
        this.distanceInKm = distanceInKm;
    }


    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSourceLocation() {
        return sourceLocation;
    }

    public void setSourceLocation(String sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(String distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

}
