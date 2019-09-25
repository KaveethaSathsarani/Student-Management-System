package com.example.studentmanagementsystem.DBHandlers;

public class AdminModel {
    private  String name;
    private String password;
    private String adminID;
    public AdminModel(){

    }
    public AdminModel(String adminID, String name, String password) {
        this.adminID = adminID;
        this.name = name;
        this.password = password;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
