package com.example.myhaui.model;

public class Friend {
    private int id;
    private int userId;
    private String code;
    private String fullName;
    private String phoneNumber;

    public Friend(int id, int userId, String code, String fullName, String phoneNumber) {
        this.id = id;
        this.userId = userId;
        this.code = code;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
