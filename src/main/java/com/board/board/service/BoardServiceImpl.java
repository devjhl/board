package com.board.board.service;

import com.board.board.domain.board.Board;
import com.board.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public List<Board> getBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Optional<Board> getBoard(Long id) {
        long count = boardRepository.count();
        count++;
        return boardRepository.findById(id);
    }

    @Override
    public boolean createBoard(Board board) {
       boardRepository.save(board);
        return true;
    }

    @Override
    public boolean updateBoard(Long id, Board board) {
        if(boardRepository.existsById(board.getId())) {
            boardRepository.save(board);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteBoard(Long id) {
        if(boardRepository.existsById(id)) {
            boardRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
