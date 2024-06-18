package com.board.board.controller.user;

import com.board.board.domain.user.Role;
import com.board.board.domain.user.User;
import com.board.board.service.UserService2;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService2 userService;

    //회원가입 페이지
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // 회원가입 페이지
    }
    //회원가입
    @PostMapping("/register")
    public String register(@Valid User user) {
        user.setRole(Role.USER);
        userService.save(user);
        return "redirect:/login";
    }
    //로그인 페이지
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }




}
