package com.board.board.controller.board;

import com.board.board.domain.board.Board;
import com.board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    // 전체 글 목록 조회
    @GetMapping
    public List<Board> getBoards(Model model) {
        List<Board> boardList = boardService.getBoards();
        model.addAttribute("boardList", boardList);
        return boardList;
    }

    // 글 상세보기
    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoard(@PathVariable Long id) {

        Optional<Board> board = boardService.getBoard(id);
        return board.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    // 글 작성 페이지
    @ResponseBody
    @GetMapping("/write")
    public ModelAndView showWritePage(Model model) {
        ModelAndView mav = new ModelAndView("write");
        mav.addObject("board", new Board());
        return mav;
    }

    // 글 작성
    @ResponseBody
    @PostMapping("/write")
    public ResponseEntity<Board> createBoard(@ModelAttribute Board board) {
        boardService.createBoard(board);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    // 글 수정
    @ResponseBody
    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@RequestBody Board board) {
        boardService.updateBoard(board);
        return ResponseEntity.ok(board);
    }
    // 글 삭제
    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}
