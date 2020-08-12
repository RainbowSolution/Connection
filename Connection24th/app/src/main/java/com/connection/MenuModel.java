package com.connection;

public class MenuModel {

    public String menuName;
    public boolean hasChildren, isGroup;
    public int itemimage;

    public MenuModel(String menuName, boolean hasChildren, boolean isGroup, int itemimage) {
        this.menuName = menuName;
        this.hasChildren = hasChildren;
        this.isGroup = isGroup;
        this.itemimage = itemimage;
    }
}