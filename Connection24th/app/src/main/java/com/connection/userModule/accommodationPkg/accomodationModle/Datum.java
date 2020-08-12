package com.connection.userModule.accommodationPkg.accomodationModle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("product_images")
    @Expose
    private String productImages;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("payment")
    @Expose
    private String payment;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("item_count")
    @Expose
    private String itemCount;
    @SerializedName("cat_images")
    @Expose
    private String catImages;

    /**
     * No args constructor for use in serialization
     */
    public Datum() {
    }

    /**
     * @param address
     * @param city
     * @param mobile
     * @param description
     * @param categoryName
     * @param productName
     * @param itemCount
     * @param duration
     * @param catId
     * @param productImages
     * @param catImages
     * @param payment
     * @param id
     * @param time
     * @param categoryId
     */
    public Datum(String id, String productName, String description, String productImages, String categoryId, String city, String duration, String mobile, String time, String address, String payment, String catId, String categoryName, String itemCount, String catImages) {
        super();
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.productImages = productImages;
        this.categoryId = categoryId;
        this.city = city;
        this.duration = duration;
        this.mobile = mobile;
        this.time = time;
        this.address = address;
        this.payment = payment;
        this.catId = catId;
        this.categoryName = categoryName;
        this.itemCount = itemCount;
        this.catImages = catImages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductImages() {
        return productImages;
    }

    public void setProductImages(String productImages) {
        this.productImages = productImages;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getCatImages() {
        return catImages;
    }

    public void setCatImages(String catImages) {
        this.catImages = catImages;
    }

}
