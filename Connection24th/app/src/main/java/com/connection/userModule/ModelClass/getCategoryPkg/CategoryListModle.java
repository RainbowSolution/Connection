package com.connection.userModule.ModelClass.getCategoryPkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryListModle {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Category> data = null;

    /**
     * No args constructor for use in serialization
     */
    public CategoryListModle() {
    }

    /**
     * @param data
     * @param message
     * @param status
     */
    public CategoryListModle(Boolean status, String message, List<Category> data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }

}
