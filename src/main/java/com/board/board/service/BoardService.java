package com.board.board.service;

import com.board.board.domain.board.Board;
import com.board.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //글 리스트
    public List<Board> boardList(){
        return boardRepository.findAll();
    }
    //글 상세보기
    public Board boardDetail(Long id){
        boardRepository.count(); // 조회수
        return boardRepository.findById(id).orElse(null);
    }
   //글 작성
    public boolean addBoard(Board board){
        boardRepository.save(board);
        return true;
    }
    /*
    //글 수정
    boolean editBoard(Board board){

    }
    //글 삭제
    boolean deleteBoard(int id){

    }*/
}
