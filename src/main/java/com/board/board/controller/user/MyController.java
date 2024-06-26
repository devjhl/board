package com.board.board.controller.user;

import com.board.board.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@RequiredArgsConstructor
@Controller
public class MyController {

    private final CustomUserDetailsService customUserDetailsService;

    @ModelAttribute
    public User addUserToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (authentication != null && authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
            user = (User) authentication.getPrincipal();
            model.addAttribute("loggedInUser", user);
            com.board.board.domain.user.User users = customUserDetailsService.getUserByUsername(user.getUsername());
            model.addAttribute("LoginUser", user);
            com.board.board.domain.user.User userByUsername = customUserDetailsService.getUserByUsername(user.getUsername());
            model.addAttribute("LoginUsers", userByUsername);
        }
        return user;
    }

    @GetMapping("/users/my")
    public String myPage(Model model) {
        return "my";
    }
}
