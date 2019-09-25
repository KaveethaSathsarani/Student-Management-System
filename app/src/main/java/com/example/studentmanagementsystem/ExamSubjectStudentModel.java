package com.example.studentmanagementsystem;

public class ExamSubjectStudentModel {
    private ExamModel examModel;
    private SubjectModel subjectModel;
    private StudentModel studentModel;
    private double marks;
    private String grade;

    public ExamSubjectStudentModel(){

    }
    public ExamSubjectStudentModel(ExamModel examModel, SubjectModel subjectModel, StudentModel studentModel, double marks, String grade) {
        this.examModel = examModel;
        this.subjectModel = subjectModel;
        this.studentModel = studentModel;
        this.marks = marks;
        this.grade = grade;
    }

    public ExamModel getExamModel() {
        return examModel;
    }

    public void setExamModel(ExamModel examModel) {
        this.examModel = examModel;
    }

    public SubjectModel getSubjectModel() {
        return subjectModel;
    }

    public void setSubjectModel(SubjectModel subjectModel) {
        this.subjectModel = subjectModel;
    }

    public StudentModel getStudentModel() {
        return studentModel;
    }

    public void setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
