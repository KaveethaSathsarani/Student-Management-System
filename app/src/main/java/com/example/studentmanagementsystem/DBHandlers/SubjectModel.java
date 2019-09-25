package com.example.studentmanagementsystem.DBHandlers;

import com.google.firebase.database.Exclude;

public class SubjectModel {
    private String subId;
    private String subName;
    private String teacherName;
    private String venue;
    private String day;
    private String time;
    private String endtime;
    //private String key;


    public SubjectModel(){

    }





    public SubjectModel(String subId, String subName, String teacherName, String venue, String day, String time, String endtime) {
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


    //@Exclude
    //public String getKey() {
        //return key;
    //}

    //@Exclude
    //public void setkey(String key) {
       // this.key = key;
    //}

}
