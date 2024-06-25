package com.board.board.config;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addUserToModel(@AuthenticationPrincipal User user, Model model) {
        System.out.println("=======================================user: " + user);
        if (user != null) {
            model.addAttribute("loggedInUser", user);
            model.addAttribute("loggedInUserName", user.getUsername());
            model.addAttribute("loggedInUserEmail", user.getUsername());

        }
    }
}
