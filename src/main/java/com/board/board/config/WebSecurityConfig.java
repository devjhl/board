package com.board.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HttpSecurity httpSecurity) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                            authHttp -> authHttp
                                    .requestMatchers("/users").permitAll()
                                    .requestMatchers("/users/main","/users/logout").authenticated()
                                    .requestMatchers("/users/signup").anonymous()
                )
                .formLogin(
                        formLogin -> formLogin
                                .loginPage("/users") // 로그인 경로
                                .defaultSuccessUrl("/users/main") // 로그인 성공시 디폴트 경로
                                .failureUrl("/users/login?fail") // 로그인 실패시 경로
                                .permitAll()
                )
                .logout(
                        logOut -> logOut
                                .logoutUrl("/users/logout") // 로그아웃 경로
                                .logoutSuccessUrl("/users") // 로그아웃 성공시 경로
                );
                return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
