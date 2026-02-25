package com.example.demo.controller;

import com.example.demo.service.URLShortenerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class URLController {

    private final URLShortenerService service;

    public URLController(URLShortenerService service) {
        this.service = service;
    }

    // Create short URL
    @GetMapping("/shorten")
    public String shorten(@RequestParam String url) {
        return service.shortenURL(url);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        String originalUrl = service.redirect(shortCode);

        if (originalUrl == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    
 
  
    }
}