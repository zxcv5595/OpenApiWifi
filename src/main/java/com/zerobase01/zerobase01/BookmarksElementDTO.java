package com.zerobase01.zerobase01;

import java.sql.Timestamp;

public class BookmarksElementDTO {
    private int id;
    private String bookmarkName;
    private String wifiName;
    private Timestamp createdAt;

    public BookmarksElementDTO(int id, String bookmarkName, String wifiName, Timestamp createdAt) {
        this.id = id;
        this.bookmarkName = bookmarkName;
        this.wifiName = wifiName;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getBookmarkName() {
        return bookmarkName;
    }

    public String getWifiName() {
        return wifiName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
