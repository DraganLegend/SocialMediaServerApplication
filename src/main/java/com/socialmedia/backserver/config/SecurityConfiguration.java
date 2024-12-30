package com.socialmedia.backserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> {
                    var config = new org.springframework.web.cors.CorsConfiguration();
                    config.addAllowedOrigin("*"); // 允許所有來源（根據需求設定）
                    config.addAllowedMethod("*");
                    config.addAllowedHeader("*");
                    return config;
                }))
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/login", "/api/emotion/stats", "/api/emotion/random")) // 忽略 CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/login").permitAll() // 允許訪問
                        .requestMatchers("/api/emotion/stats", "/api/emotion/random").permitAll() // 允許 emotion 路徑
                        .anyRequest().authenticated()); // 其他需要認證
        // 直接禁用 HTTP Basic 和表單登錄
        http.httpBasic().disable();
        http.formLogin().disable();
        return http.build();
    }
}