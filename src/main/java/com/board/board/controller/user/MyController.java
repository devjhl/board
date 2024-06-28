package com.board.board.controller.user;

import com.board.board.dto.UserRequestDto;
import com.board.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/my")
@RequiredArgsConstructor
@Controller
public class MyController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;


    @GetMapping
    public String myPage(Model model) {
        logger.info("myPage() 호출됨");
        return "user/my";
    }

    @ResponseBody
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequestDto dto) {
        logger.info("updateUser() 호출됨: id={}, dto={}", id, dto);
        userService.modify(dto);
        return ResponseEntity.ok("회원정보 수정 성공");
    }



}
