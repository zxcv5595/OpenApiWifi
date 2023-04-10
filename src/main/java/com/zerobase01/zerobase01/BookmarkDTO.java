package com.zerobase01.zerobase01;

import java.sql.Timestamp;

public class BookmarkDTO {
    private int id;
    private String name;
    private int sequence;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public BookmarkDTO(int id, String name, int sequence, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.sequence = sequence;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}

