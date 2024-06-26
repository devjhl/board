package com.board.board.service;

import com.board.board.domain.board.Board;
import com.board.board.dto.BoardResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BoardService {

    //글 리스트
    Page<Board> getBoards(Pageable pageable);
    //글 상세보기
    Optional<Board> getBoard(Long id);
    //글 작성
    boolean createBoard(Board board);
    //글 수정
    boolean updateBoard(Long id, Board board);
    //글 삭제
    boolean deleteBoard(Long id);
    //글 검색
    Page<Board> searchBoards(String keyword, Pageable pageable);

    int countBoards(Long id);

    BoardResponseDto findById(Long id);
}
