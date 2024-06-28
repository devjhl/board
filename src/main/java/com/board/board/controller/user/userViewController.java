package com.board.board.controller.user;

import com.board.board.dto.SignupUserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@Controller
public class userViewController {

    @GetMapping
    public String login() {
        return "user/login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("userDto", new SignupUserDto());
        return "user/signup";
    }
}
