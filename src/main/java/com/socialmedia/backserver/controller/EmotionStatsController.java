package com.socialmedia.backserver.controller;

import com.socialmedia.backserver.service.EmotionDataService;
import com.socialmedia.backserver.dto.EmotionStatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/emotion")
@CrossOrigin(origins = "http://localhost:8081") // 允許來自 localhost:8081 的跨域請求
public class EmotionStatsController {

    @Autowired
    private EmotionDataService emotionDataService;

    @GetMapping("/stats")
    public List<EmotionStatsDTO> getEmotionStats() {
        List<EmotionStatsDTO> stats = emotionDataService.getEmotionStats();

        // 添加日誌，確認是否有數據返回給前端
        stats.forEach(stat ->
                System.out.println("Emotion: " + stat.getEmotion() + ", Count: " + stat.getCount())
        );

        return stats;
    }
}