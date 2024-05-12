package com.example.jee.printing.platform.model;

public class MatierGroupe {
    private int id;
    private int matierId;
    private int groupeId;

    public MatierGroupe() {
    }

    public MatierGroupe(int id, int matierId, int groupeId) {
        this.id = id;
        this.matierId = matierId;
        this.groupeId = groupeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatierId() {
        return matierId;
    }

    public void setMatierId(int matierId) {
        this.matierId = matierId;
    }

    public int getGroupeId() {
        return groupeId;
    }

    public void setGroupeId(int groupeId) {
        this.groupeId = groupeId;
    }

    @Override
    public String toString() {
        return "MatierGroupe{" +
                "id=" + id +
                ", matierId=" + matierId +
                ", groupeId=" + groupeId +
                '}';
    }
}
