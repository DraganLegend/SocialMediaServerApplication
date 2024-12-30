package com.socialmedia.backserver.dto;

public class EmotionStatsDTO {
    private String emotion;
    private Long count;

    public EmotionStatsDTO(String emotion, Long count) {
        this.emotion = emotion;
        this.count = count;
    }

    public String getEmotion() {
        return emotion;
    }

    public Long getCount() {
        return count;
    }


}