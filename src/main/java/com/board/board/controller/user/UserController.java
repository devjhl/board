package com.board.board.controller.user;

import com.board.board.domain.user.Role;
import com.board.board.domain.user.User;
import com.board.board.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signup")
    public String signup(@RequestParam String username,
                                         @RequestParam String nickname,
                                         @RequestParam String password,
                                         @RequestParam String email,
                                         @RequestParam(required = false, defaultValue = "USER") String role,
                                         HttpServletResponse response) throws IOException {
        User user = User.builder()
                .username(username)
                .nickname(nickname)
                .password(password)
                .email(email)
                .role(Role.valueOf(role))
                .build();

        userService.save(user);
        return "";
    }

}

