package com.socialmedia.backserver.service;

import com.socialmedia.backserver.dto.EmotionText;
import com.socialmedia.backserver.repository.EmotionDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmotionTextService {

    @Autowired
    private EmotionDataRepository emotionDataRepository;

    public EmotionText getRandomTextByEmotion(String emotion) {
        List<Object[]> results = emotionDataRepository.findRandomByEmotionNative(emotion);
        if (results.isEmpty()) {
            return null; // 或返回一個默認值
        }
        Object[] row = results.get(0);
        return new EmotionText(
                ((Number) row[0]).longValue(), // id
                (String) row[1],              // emotion
                (String) row[2]               // text
        );
    }
}