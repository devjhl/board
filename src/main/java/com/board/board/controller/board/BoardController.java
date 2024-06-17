package com.board.board.controller.board;

import com.board.board.service.BoardService;
import com.board.board.domain.board.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
/*
    @GetMapping("/board")
    public String boardList(Model model) {
        List<Board> boardList = boardService.boardList();
        model.addAttribute("boardList", boardList);
        return "main";
    }*/

    @GetMapping("/{id}")
    public String boardDetail(@PathVariable Long id, Model model) {
        Board board = boardService.boardDetail(id);
        model.addAttribute("board", board);
        return "boardDetail";
    }
    // 글작성(GET)
    @GetMapping("/write")
    public String writePage() {
        return "write";
    }
    // 글작성(POST)

}
