

package com.example.jee.printing.platform.model;

public class Groupe {
    private int id;
    private String name;
    private int numStudents;

    public Groupe(int id, String name, int numStudents) {
        this.id = id;
        this.name = name;
        this.numStudents = numStudents;
    }

    public Groupe() {
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumStudents() {
        return numStudents;
    }

    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }
}
