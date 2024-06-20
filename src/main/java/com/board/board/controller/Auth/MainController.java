package com.board.board.controller.Auth;

import com.board.board.domain.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/main")
@Controller
public class MainController {

    @GetMapping
    public String main(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "main";
    }
}
