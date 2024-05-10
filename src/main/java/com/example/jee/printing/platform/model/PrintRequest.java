package com.example.jee.printing.platform.model;

import java.io.InputStream;
import java.time.LocalDateTime;

public class PrintRequest {
    private int id;
    private int userId;
    private int numStudents;
    private LocalDateTime arrivalDateTime;
    private InputStream document;

    // Constructors
    public PrintRequest() {
    }

    public PrintRequest(int userId, int numStudents, LocalDateTime arrivalDateTime, InputStream document) {
        this.userId = userId;
        this.numStudents = numStudents;
        this.arrivalDateTime = arrivalDateTime;
        this.document = document;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNumStudents() {
        return numStudents;
    }

    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public InputStream getDocument() {
        return document;
    }

    public void setDocument(InputStream document) {
        this.document = document;
    }
}
