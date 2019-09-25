package com.example.studentmanagementsystem.DBHandlers;

public class ExamModel {
    private String examId;
    private String examName;
    private String examTime;
    private String examVenue;
    private String examDate;
    private String grade;

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public String getExamVenue() {
        return examVenue;
    }

    public void setExamVenue(String examVenue) {
        this.examVenue = examVenue;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public ExamModel(String examId, String examName, String examTime, String examVenue, String examDate, String grade) {
        this.examId = examId;
        this.examName = examName;
        this.examTime = examTime;
        this.examVenue = examVenue;
        this.examDate = examDate;
        this.grade = grade;
    }

    public ExamModel() {
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }
}
