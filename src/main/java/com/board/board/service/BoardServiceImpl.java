package com.board.board.service;

import com.board.board.domain.board.Board;
import com.board.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Page<Board> getBoards(Pageable pageable) {
        return boardRepository.findAll(pageable);  // 변경 사항
    }

    @Override
    public Optional<Board> getBoard(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    public boolean createBoard(Board board) {
        boardRepository.save(board);
        return true;
    }

    @Override
    public boolean updateBoard(Long id, Board board) {
        Board existingBoard = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid board Id:" + id));
        existingBoard.setTitle(board.getTitle());
        existingBoard.setContent(board.getContent());
        boardRepository.save(existingBoard);
        return true;
    }

    @Override
    public boolean deleteBoard(Long id) {
        boardRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<Board> searchBoards(String keyword, Pageable pageable) {
        return boardRepository.searchBoards(keyword, pageable);  // 변경 사항
    }


}