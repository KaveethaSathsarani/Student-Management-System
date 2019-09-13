package com.example.studentmanagementsystem.DBHandlers;

public class ExamModel {
    private String examId;
    private String examName;
    public ExamModel(){

    }
    public ExamModel(String examId, String examName) {
        this.examId = examId;
        this.examName = examName;
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
