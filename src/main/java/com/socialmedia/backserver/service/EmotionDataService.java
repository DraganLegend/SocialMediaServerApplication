package com.socialmedia.backserver.service;

import com.socialmedia.backserver.dto.EmotionCountProjection;
import com.socialmedia.backserver.dto.EmotionStatsDTO;
import com.socialmedia.backserver.dto.EmotionText;
import com.socialmedia.backserver.repository.EmotionDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmotionDataService {

    @Autowired
    private EmotionDataRepository emotionDataRepository;

    public List<EmotionStatsDTO> getEmotionStats() {
        // 使用 Native Query 方法
        List<EmotionCountProjection> results = emotionDataRepository.countByEmotionNative();
        return results.stream()
                .map(projection -> new EmotionStatsDTO(projection.getEmotion(), projection.getCount()))
                .collect(Collectors.toList());
    }


}