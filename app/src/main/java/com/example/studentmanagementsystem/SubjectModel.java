package com.example.studentmanagementsystem;

public class SubjectModel {
    private String subId;
    private String subName;

    public SubjectModel(String subId, String subName) {
        this.subId = subId;
        this.subName = subName;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }
}
