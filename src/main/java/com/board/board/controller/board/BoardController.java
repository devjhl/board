package com.board.board.controller.board;

import com.board.board.domain.board.Board;
import com.board.board.domain.user.User;
import com.board.board.service.BoardService;
import com.board.board.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping
    public String getBoards(Model model, @AuthenticationPrincipal User user,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(required = false) String keyword) {
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
        return "boards";
    }

    @GetMapping("/{id}")
    public String getBoard(@PathVariable Long id, Model model) {
        Optional<Board> board = boardService.getBoard(id);
        model.addAttribute("board", board.get());
        return "detail";
    }

    @GetMapping("/write")
    public String showWritePage(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        model.addAttribute("board", new Board());
        return "write";
    }

    @PostMapping("/write")
    public String createBoard(Board board, @AuthenticationPrincipal User user) {
        board.setUser(user);
        boardService.createBoard(board);
        return "redirect:/boards";
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody Board board, HttpServletResponse response) throws IOException {
        response.sendRedirect("boards");
        boardService.updateBoard(id, board);
        return ResponseEntity.ok(board);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }

}
