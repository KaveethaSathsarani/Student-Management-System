package com.example.studentmanagementsystem;

import java.io.Serializable;

public class SubjectModel implements Serializable {
    private String subId;
    private String subName;
    private String time;
    private String endtime;
    private String teacherName;
    private String venue;
    private String day;
    public SubjectModel(){

    }

    public SubjectModel(String subId, String subName, String teacherName, String venue, String day, String time, String endtime) {

        if(subId.trim().equals("")){

            subId = "Subject Id Not Found";

        }

        this.subId = subId;
        this.subName = subName;
        this.teacherName = teacherName;
        this.venue = venue;
        this.day = day;
        this.time = time;
        this.endtime = endtime;
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


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }



}
