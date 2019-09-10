package com.example.studentmanagementsystem;

public class StudentModel {
    private  String studentID;
    private String studentName;
    private String school;
    private int ContactNo;
    private String address;
    private String gName;
    private int gcNo;
    private String password;
    public StudentModel(){

    }
    public StudentModel(String studentID, String studentName, String school, int contactNo, String address, String gName, int gcNo, String password) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.school = school;
        ContactNo = contactNo;
        this.address = address;
        this.gName = gName;
        this.gcNo = gcNo;
        this.password = password;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getContactNo() {
        return ContactNo;
    }

    public void setContactNo(int contactNo) {
        ContactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public int getGcNo() {
        return gcNo;
    }

    public void setGcNo(int gcNo) {
        this.gcNo = gcNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
