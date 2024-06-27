package com.board.board.controller.comment;


import com.board.board.controller.user.MyController;
import com.board.board.domain.user.User;
import com.board.board.dto.CommentRequestDto;
import com.board.board.service.CommentService;
import com.board.board.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentApiController {

    private final CommentService commentService;
    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<?> commentSave(@PathVariable Long id, @RequestBody CommentRequestDto dto, Model model,@AuthenticationPrincipal UserDetails userDetails) {
        User user = customUserDetailsService.getUserByUsername(userDetails.getUsername());

        commentService.commentSave(user.getNickname(), id, dto);

        // JSON 형식의 응답 반환
        return ResponseEntity.ok().body("{\"status\":\"success\"}");
    }

    //업데이트
    @PutMapping({"/posts/{postsId}/comments/{id}"})
    public ResponseEntity<Long> update(@PathVariable Long postsId,@PathVariable Long id,@RequestBody CommentRequestDto dto) {
        commentService.update(postsId, id , dto);
        return ResponseEntity.ok(id);
    }


    //삭제
    @DeleteMapping("/posts/{postsId}/comments/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long postsId, @PathVariable Long id) {
        System.out.println("Delete request received with postsId: " + postsId + ", id: " + id); // 디버깅용 로그
        commentService.delete(postsId, id);
        return ResponseEntity.ok(id);
    }
}
