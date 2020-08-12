package com.connection.userModule.ModelClass.getCategoryPkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

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
    public Category() {
    }

    /**
     * @param catId
     * @param catImages
     * @param categoryName
     * @param itemCount
     */
    public Category(String catId, String categoryName, String itemCount, String catImages) {
        super();
        this.catId = catId;
        this.categoryName = categoryName;
        this.itemCount = itemCount;
        this.catImages = catImages;
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
