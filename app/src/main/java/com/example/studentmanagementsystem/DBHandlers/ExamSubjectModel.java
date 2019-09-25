package com.example.studentmanagementsystem.DBHandlers;

public class ExamSubjectModel {
    private ExamModel exam;
    private SubjectModel subject;

    public ExamSubjectModel(){

    }

    public ExamSubjectModel(ExamModel exam, SubjectModel subject) {
        this.exam = exam;
        this.subject = subject;
    }


    public ExamModel getExam() {
        return exam;
    }

    public void setExam(ExamModel exam) {
        this.exam = exam;
    }

    public SubjectModel getSubject() {
        return subject;
    }

    public void setSubject(SubjectModel subject) {
        this.subject = subject;
    }
}
