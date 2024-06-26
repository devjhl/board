package com.board.board.controller.board;


import com.board.board.controller.user.MyController;
import com.board.board.domain.user.User;
import com.board.board.dto.CommentRequestDto;
import com.board.board.service.CommentService;
import com.board.board.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentApiController {

    private final CommentService commentService;
    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<?> commentSave(@PathVariable Long id, @RequestBody CommentRequestDto dto, Model model) {
        // 로그인 정보
        MyController myController = new MyController(customUserDetailsService);
        org.springframework.security.core.userdetails.User loginUser = myController.addUserToModel(model);
        User user = customUserDetailsService.getUserByUsername(loginUser.getUsername());

        commentService.commentSave(user.getNickname(), id, dto);

        // JSON 형식의 응답 반환
        return ResponseEntity.ok().body("{\"status\":\"success\"}");
    }
}
