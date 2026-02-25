package com.example.demo.repository;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class URLCache {

    private Map<String, String> cache = new HashMap<>();

    public void put(String shortCode, String originalURL) {
        cache.put(shortCode, originalURL);
    }

    public String get(String shortCode) {
        return cache.get(shortCode);
    }

    public boolean contains(String shortCode) {
        return cache.containsKey(shortCode);
    }
}