package com.socialmedia.backserver.controller;

import com.socialmedia.backserver.dto.EmotionText;
import com.socialmedia.backserver.service.EmotionTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/emotion")
@CrossOrigin(origins = "http://localhost:8081") // 允許來自 localhost:8081 的跨域請求
public class EmotionDataController {

    @Autowired
    private EmotionTextService emotionTextService;

    @GetMapping("/random")
    public ResponseEntity<?> getRandomTextByEmotion(@RequestParam String emotion) {
        try {
            EmotionText randomText = emotionTextService.getRandomTextByEmotion(emotion);
            if (randomText == null) {
                return ResponseEntity.status(404).body(Map.of("error", "No text found for the specified emotion."));
            }
            return ResponseEntity.ok(randomText);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "An internal server error occurred.", "details", e.getMessage()));
        }
    }
}