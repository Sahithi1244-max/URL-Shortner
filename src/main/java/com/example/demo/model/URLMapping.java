package com.example.demo.model;

public class URLMapping {

    private String shortCode;
    private String originalURL;
    private int clickCount;

    public URLMapping(String shortCode, String originalURL) {
        this.shortCode = shortCode;
        this.originalURL = originalURL;
        this.clickCount = 0;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void incrementClickCount() {
        clickCount++;
    }
}