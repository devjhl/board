package com.board.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
                .cors(cors -> cors.disable()) // CORS 보호 비활성화
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/auth/**", "/login", "/register").permitAll() // 인증 없이 접근 가능한 경로 설정
                        .requestMatchers("/boards/**").permitAll() // 인증 없이 접근 가능한 경로 설정
                        .anyRequest().authenticated() // 그 외의 모든 요청은 인증 필요
                )
                .formLogin(form -> form
                        .loginPage("/auth/login") // 커스텀 로그인 페이지 설정
                        .defaultSuccessUrl("/", true) // 로그인 성공 후 이동할 기본 페이지 설정
                        .permitAll() // 로그인 페이지는 인증 없이 접근 가능
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 URL 설정
                        .permitAll() // 로그아웃은 인증 없이 접근 가능
                );

        return http.build(); // 시큐리티 필터 체인 생성
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}