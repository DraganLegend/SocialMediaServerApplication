package com.socialmedia.backserver;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptCheck {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // 假設你從數據庫中獲取到的密碼
        String hashedPassword = "$2a$10$7/fDTeoQxHd0cCmFh1Qck.2K.5fscEBoky8/M2JhgRvG2U5sYbXYq";

        // 用戶輸入的原始密碼
        String rawPassword = "password";

        // 驗證密碼是否匹配
        boolean matches = encoder.matches(rawPassword, hashedPassword);

        // 輸出檢查結果
        System.out.println("Does the password match? " + matches);
    }
}