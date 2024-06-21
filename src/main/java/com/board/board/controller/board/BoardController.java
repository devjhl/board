package com.board.board.controller.board;

import com.board.board.domain.board.Board;
import com.board.board.domain.user.User;
import com.board.board.service.BoardService;
import com.board.board.service.UserDetailsService;
import com.board.board.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@RequestMapping("/boards")
@Controller
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;


    // 전체 글 목록 조회
    @GetMapping
    public String getBoards(Model model,@AuthenticationPrincipal User user) {
        List<Board> boardList = boardService.getBoards();
        model.addAttribute("boardList", boardList);
        model.addAttribute("user", user);
        return "boards";
    }

    // 글 상세보기
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Board> getBoard(@PathVariable Long id) {

        Optional<Board> board = boardService.getBoard(id);

        return board.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 글 작성 페이지
    @GetMapping("/write")
    public String showWritePage(Model model,@AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        model.addAttribute("board", new Board());
        return "write";
    }

    // 글 작성
    @PostMapping("/write")
    @ResponseBody
    public String createBoard(Board board,@AuthenticationPrincipal User user) {
        board.setUser(user); // 작성자
        boardService.createBoard(board);
        return "redirect:/boards";
    }

    // 글 수정
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody Board board) {
        boardService.updateBoard(id, board);
        return ResponseEntity.ok(board);
    }

    // 글 삭제
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }

    public String loginInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        }
        return username;
    }

}