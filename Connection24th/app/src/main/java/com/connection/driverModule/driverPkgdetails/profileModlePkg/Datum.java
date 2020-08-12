
package com.connection.driverModule.driverPkgdetails.profileModlePkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

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
    @SerializedName("vehicle_photo")
    @Expose
    private String vehiclePhoto;
    @SerializedName("police_verification_certificate")
    @Expose
    private String policeVerificationCertificate;
    @SerializedName("permit")
    @Expose
    private String permit;
    @SerializedName("registration_certificate")
    @Expose
    private String registrationCertificate;
    @SerializedName("driver_verification_status")
    @Expose
    private String driverVerificationStatus;

    @SerializedName("profile_pic")
    @Expose
    private String profile_pic;


    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }


    /**
     * No args constructor for use in serialization
     */
    public Datum() {
    }

    /**
     * @param vehicleName
     * @param driverLicenseNumber
     * @param driverVerificationStatus
     * @param name
     * @param permit
     * @param vehicleNumber
     * @param registrationCertificate
     * @param policeVerificationCertificate
     * @param vehiclePhoto
     */
    public Datum(String name, String vehicleName, String vehicleNumber, String driverLicenseNumber, String vehiclePhoto, String policeVerificationCertificate, String permit, String registrationCertificate, String driverVerificationStatus) {
        super();
        this.name = name;
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
        this.driverLicenseNumber = driverLicenseNumber;
        this.vehiclePhoto = vehiclePhoto;
        this.policeVerificationCertificate = policeVerificationCertificate;
        this.permit = permit;
        this.registrationCertificate = registrationCertificate;
        this.driverVerificationStatus = driverVerificationStatus;
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

    public String getVehiclePhoto() {
        return vehiclePhoto;
    }

    public void setVehiclePhoto(String vehiclePhoto) {
        this.vehiclePhoto = vehiclePhoto;
    }

    public String getPoliceVerificationCertificate() {
        return policeVerificationCertificate;
    }

    public void setPoliceVerificationCertificate(String policeVerificationCertificate) {
        this.policeVerificationCertificate = policeVerificationCertificate;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    public String getRegistrationCertificate() {
        return registrationCertificate;
    }

    public void setRegistrationCertificate(String registrationCertificate) {
        this.registrationCertificate = registrationCertificate;
    }

    public String getDriverVerificationStatus() {
        return driverVerificationStatus;
    }

    public void setDriverVerificationStatus(String driverVerificationStatus) {
        this.driverVerificationStatus = driverVerificationStatus;
    }

}
