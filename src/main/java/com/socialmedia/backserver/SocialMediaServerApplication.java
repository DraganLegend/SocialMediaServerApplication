package com.socialmedia.backserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.socialmedia.backserver.model")
public class SocialMediaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialMediaServerApplication.class, args);
    }

}
