package com.connection.driverModule.driverChatPkg;

import java.util.ArrayList;


public class Consersation {
    private ArrayList<ChatModle> listMessageData;
    public Consersation(){
        listMessageData = new ArrayList<>();
    }

    public ArrayList<ChatModle> getListMessageData() {
        return listMessageData;
    }
}
