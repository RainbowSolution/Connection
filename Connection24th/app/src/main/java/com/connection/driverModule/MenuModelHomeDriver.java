package com.connection.driverModule;

public class MenuModelHomeDriver {

    public String menuName;
    public boolean hasChildren, isGroup;
    public int itemimage;

    public MenuModelHomeDriver(String menuName, boolean hasChildren, boolean isGroup, int itemimage) {
        this.menuName = menuName;
        this.hasChildren = hasChildren;
        this.isGroup = isGroup;
        this.itemimage = itemimage;
    }
}
