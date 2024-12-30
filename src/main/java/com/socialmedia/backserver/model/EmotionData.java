package com.socialmedia.backserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "EmotionData")
public class EmotionData {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "emotion", nullable = false, length = 50)
    private String emotion;

    @Lob
    @Column(name = "text", nullable = false)
    private String text;

}