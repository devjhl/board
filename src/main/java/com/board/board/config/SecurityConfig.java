package com.board.board.config;

import com.board.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder; // PasswordEncoder를 주입받음

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
                .cors(cors -> cors.disable()) // CORS 보호 비활성화
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll() // 모든 요청을 인증 없이 접근 가능
                )
                .formLogin(form -> form
                        .loginPage("/auth/login") // 커스텀 로그인 페이지 설정
                        .defaultSuccessUrl("/", true) // 로그인 성공 후 이동할 기본 페이지 설정
                        .failureUrl("/auth/login?error=true") // 로그인 실패 시 이동할 페이지 설정
                        .permitAll() // 로그인 페이지는 인증 없이 접근 가능
                )
                .logout(logout -> logout
                        .logoutUrl("/auth/logout") // 로그아웃 URL 설정
                        .logoutSuccessUrl("/") // 로그아웃 성공 후 홈 페이지로 이동
                        .permitAll() // 로그아웃은 인증 없이 접근 가능
                );

        return http.build(); // 시큐리티 필터 체인 생성
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userService) // UserService를 UserDetailsService로 사용
                .passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }
}