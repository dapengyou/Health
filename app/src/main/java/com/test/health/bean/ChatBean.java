package com.test.health.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ChatBean implements MultiItemEntity {
    public static final int JION = 1;
    public static final int NORMAL = 2;


    private int itemType;
    private String message;
    private String userName;
    private String userImg;


    public ChatBean(int itemType, String message, String userName, String userImg) {
        this.itemType = itemType;
        this.message = message;
        this.userName = userName;
        this.userImg = userImg;
    }


    public String getMessage() {
        return message;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
