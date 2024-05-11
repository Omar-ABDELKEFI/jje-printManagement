package com.example.jee.printing.platform.model;

import com.example.jee.printing.platform.model.Matier;

import com.example.jee.printing.platform.utils.ConnectionUtils;

public class Matier {
    private int id;
    private String name;

    public Matier(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Matier() {
        
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

    @Override
    public String toString() {
        return "Matier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}

