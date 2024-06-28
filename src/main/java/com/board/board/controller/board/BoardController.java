package com.board.board.controller.board;

import com.board.board.domain.board.Board;
import com.board.board.domain.user.User;
import com.board.board.dto.BoardResponseDto;
import com.board.board.dto.CommentResponseDto;
import com.board.board.service.BoardService;
import com.board.board.service.CustomUserDetailsService;
import com.board.board.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
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
    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping
    public String getBoards(Model model, @AuthenticationPrincipal User user,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(required = false) String keyword) {
        try {
            Pageable pageable = PageRequest.of(page, 10, Sort.by("createDate").descending());
            Page<Board> boardPage;

            if (keyword != null && !keyword.isEmpty()) {
                boardPage = boardService.searchBoards(keyword, pageable);
                model.addAttribute("keyword", keyword);
            } else {
                boardPage = boardService.getBoards(pageable);
            }

            model.addAttribute("boardList", boardPage.getContent());
            model.addAttribute("user", user);
            model.addAttribute("totalPages", boardPage.getTotalPages());
            model.addAttribute("currentPage", page);
            return "board/boards";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/{id}")
    public String getBoard(@PathVariable Long id, Model model) {
        try {
            Optional<Board> board = boardService.getBoard(id);
            BoardResponseDto dto = boardService.findById(id);
            List<CommentResponseDto> comments = dto.getComments();

            if (board.isPresent()) {
                model.addAttribute("board", board.get());
            }

            //댓글 관련
            if (comments != null && !comments.isEmpty()) {
                model.addAttribute("comments", comments);
            }

            return "board/detail";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/write")
    public String showWritePage(Model model) {
        return "board/write";
    }

    @PostMapping("/write")
    public String createBoard(Board board, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            User userByUsername = customUserDetailsService.getUserByUsername(userDetails.getUsername());
            board.setUser(userByUsername);
            boardService.createBoard(board);
            return "redirect:/boards";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody Board board, HttpServletResponse response) throws IOException {
        try {
            response.sendRedirect("boards");
            boardService.updateBoard(id, board);
            return ResponseEntity.ok(board);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        try {
            boardService.deleteBoard(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
