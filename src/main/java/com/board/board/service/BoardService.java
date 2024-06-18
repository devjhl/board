package com.board.board.service;

import com.board.board.domain.board.Board;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface BoardService {

    //글 리스트
    List<Board> getBoards();
    //글 상세보기
    Optional<Board> getBoard(Long id);
    //글 작성
    boolean createBoard(Board board);
    //글 수정
    boolean updateBoard(Board board);
    //글 삭제
    boolean deleteBoard(Long id);
}
