package com.example.demo.service;

import com.example.demo.model.URLMapping;
import com.example.demo.repository.URLCache;
import com.example.demo.repository.URLRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class URLShortenerService {

    private final URLRepository repository;
    private final URLCache cache;
    private final Random random = new Random();

    private static final String CHARACTERS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final int SHORT_CODE_LENGTH = 6;

    public URLShortenerService(URLRepository repository, URLCache cache) {
        this.repository = repository;
        this.cache = cache;
    }

    private String generateShortCode() {

        String shortCode;

        do {
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
                int index = random.nextInt(CHARACTERS.length());
                builder.append(CHARACTERS.charAt(index));
            }

            shortCode = builder.toString();

        } while (repository.findByShortCode(shortCode) != null);

        return shortCode;
    }

    public String shortenURL(String originalURL) {

        if (originalURL == null || originalURL.isEmpty()) {
            throw new IllegalArgumentException("Original URL cannot be empty");
        }

        String shortCode = generateShortCode();

        URLMapping mapping = new URLMapping(shortCode, originalURL);
        repository.save(mapping);

        return shortCode;
    }
    public String redirect(String shortCode) {

        if (shortCode == null || shortCode.isEmpty()) {
            return null;
        }

        if (cache.contains(shortCode)) {
            System.out.println("Cache Hit!");
            return cache.get(shortCode);
        }

        System.out.println("Cache Miss! Checking Database...");

        URLMapping mapping = repository.findByShortCode(shortCode);

        if (mapping == null) {
            return null;
        }

        cache.put(shortCode, mapping.getOriginalURL());
        mapping.incrementClickCount();

        return mapping.getOriginalURL();
    }
}