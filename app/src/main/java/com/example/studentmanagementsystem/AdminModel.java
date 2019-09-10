package com.example.studentmanagementsystem;

public class AdminModel {
    private  String userName;
    private String password;
    public AdminModel(){

    }
    public AdminModel(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
