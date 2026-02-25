package com.example.demo.repository;

import com.example.demo.model.URLMapping;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class URLRepository {

    private Map<String, URLMapping> storage = new HashMap<>();

    public void save(URLMapping mapping) {
        storage.put(mapping.getShortCode(), mapping);
    }

    public URLMapping findByShortCode(String shortCode) {
        return storage.get(shortCode);
    }
}