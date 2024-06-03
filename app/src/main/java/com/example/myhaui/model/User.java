package com.example.myhaui.model;

public class User {
    private int _id;
    private String code;
    private String fullName;
    private String password;
    private String gender;
    private String phoneNumber;
    private String className;
    private String faculty;
    private String address;

    public User(int _id, String code, String fullName, String password, String gender, String phoneNumber, String className, String faculty, String address) {
        this._id = _id;
        this.code = code;
        this.fullName = fullName;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.className = className;
        this.faculty = faculty;
        this.address = address;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
