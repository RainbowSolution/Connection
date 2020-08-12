package com.connection.userModule.ModelClass;

public class HomeModel {

    private String name, totalCounts, colorStr;
    private Integer image;

    public HomeModel(String name, Integer image, String totalCounts, String colorStr) {
        this.name = name;
        this.image = image;
        this.totalCounts = totalCounts;
        this.colorStr = colorStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(String totalCounts) {
        this.totalCounts = totalCounts;
    }

    public String getColorStr() {
        return colorStr;
    }

    public void setColorStr(String colorStr) {
        this.colorStr = colorStr;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }


}
