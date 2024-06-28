package com.board.board.controller.user;

import com.board.board.domain.user.Role;
import com.board.board.domain.user.User;
import com.board.board.dto.SignupUserDto;
import com.board.board.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("userDto") SignupUserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // 유효성 검사 실패시 입력 데이터 값을 유지하고, 오류 메시지를 모델에 추가
            model.addAttribute("userDto", userDto);
            return "user/signup";
        }
        userService.save(userDto);
        return "redirect:/users";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
        }
        return "user/login";
    }



}

