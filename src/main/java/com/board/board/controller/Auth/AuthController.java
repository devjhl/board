package com.board.board.controller.Auth;

import com.board.board.domain.user.User;
import com.board.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String nickname,
                           @RequestParam String password,
                           @RequestParam String email) {
        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setPassword(password);
        user.setEmail(email);

        userService.registerUser(user);

        return "redirect:/auth/login";
    }

    @ResponseBody
    @GetMapping("/me")
    public ResponseEntity<UserDetails> me(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userDetails);
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // 로그인 페이지로 이동
    }
}