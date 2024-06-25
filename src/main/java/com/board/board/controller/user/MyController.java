package com.board.board.controller.user;

import com.board.board.service.CustomUserDetailsService;
import com.board.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@RequiredArgsConstructor
@Controller
public class MyController {

    @Autowired
    private final CustomUserDetailsService customUserDetailsService;

    @ModelAttribute
    public void addUserToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            model.addAttribute("loggedInUser", user);
            com.board.board.domain.user.User users = customUserDetailsService.getUserByUsername(user.getUsername());
            model.addAttribute("LoginUser", user);
            com.board.board.domain.user.User userByUsername = customUserDetailsService.getUserByUsername(user.getUsername());
            model.addAttribute("LoginUsers", userByUsername);
        }
    }

    @GetMapping("/users/my")
    public String myPage(Model model) {
        return "my";
    }
}
