package com.socialmedia.backserver.dto;

public class EmotionText {
    private Long id;
    private String emotion; // 情感標籤
    private String text; // 文字內容

    public EmotionText(Long id, String emotion, String text) {
        this.emotion = emotion;
        this.text = text;
    }

    public String getEmotion() {
        return emotion;
    }

    public String getText() {
        return text;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }
}