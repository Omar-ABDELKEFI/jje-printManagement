package com.example.jee.printing.platform.model;

public class Task {
    private String teacherName;
    private int numCopies;
    private String receptionDate;
    private String document;
    private String fileName;

    public Task(String teacherName, int numCopies, String receptionDate, String document, String fileName) {
        this.teacherName = teacherName;
        this.numCopies = numCopies;
        this.receptionDate = receptionDate;
        this.document = document;
        this.fileName = fileName;

    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getNumCopies() {
        return numCopies;
    }

    public void setNumCopies(int numCopies) {
        this.numCopies = numCopies;
    }

    public String getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(String receptionDate) {
        this.receptionDate = receptionDate;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
