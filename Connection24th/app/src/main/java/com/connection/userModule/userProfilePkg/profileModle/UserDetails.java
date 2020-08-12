
package com.connection.userModule.userProfilePkg.profileModle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetails {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("show_notification_on_home_screen")
    @Expose
    private String showNotificationOnHomeScreen;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserDetails() {
    }

    /**
     * 
     * @param showNotificationOnHomeScreen
     * @param gender
     * @param profilePic
     * @param name
     * @param id
     * @param email
     */
    public UserDetails(String id, String email, String name, String gender, String showNotificationOnHomeScreen, String profilePic) {
        super();
        this.id = id;
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.showNotificationOnHomeScreen = showNotificationOnHomeScreen;
        this.profilePic = profilePic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getShowNotificationOnHomeScreen() {
        return showNotificationOnHomeScreen;
    }

    public void setShowNotificationOnHomeScreen(String showNotificationOnHomeScreen) {
        this.showNotificationOnHomeScreen = showNotificationOnHomeScreen;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

}
