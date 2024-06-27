package com.board.board.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

        @GetMapping("/current-user")
        public UserDetails getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
            return userDetails;
        }
    }
