package com.example.studentmanagementsystem;

public class ExamStudentModel {
    private StudentModel student;
    private ExamModel exam;

    public ExamStudentModel(){

    }
    public ExamStudentModel(StudentModel student, ExamModel exam) {
        this.student = student;
        this.exam = exam;
    }

    public StudentModel getStudent() {
        return student;
    }

    public void setStudent(StudentModel student) {
        this.student = student;
    }

    public ExamModel getExam() {
        return exam;
    }

    public void setExam(ExamModel exam) {
        this.exam = exam;
    }
}
