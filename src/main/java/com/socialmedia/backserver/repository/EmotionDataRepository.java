package com.socialmedia.backserver.repository;

import com.socialmedia.backserver.dto.EmotionCountProjection;
import com.socialmedia.backserver.dto.EmotionText;
import com.socialmedia.backserver.model.EmotionData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmotionDataRepository extends CrudRepository<EmotionData, Long> {

    // 使用原生查詢（Native Query）
    @Query(value = "SELECT emotion AS emotion, COUNT(*) AS count FROM EmotionData GROUP BY emotion", nativeQuery = true)
    List<EmotionCountProjection> countByEmotionNative();

    // 查詢特定情感的隨機文本
    @Query(value = "SELECT id, emotion, text FROM EmotionData WHERE emotion = :emotion ORDER BY RAND() LIMIT 1", nativeQuery = true)
    List<Object[]> findRandomByEmotionNative(@Param("emotion") String emotion);


}