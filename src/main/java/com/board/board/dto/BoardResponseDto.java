package com.board.board.dto;

import com.board.board.domain.board.Board;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/*
게시글 정보를 리턴할 응답 클래스
Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무함참조를 방지함
 */
@Getter
public class BoardResponseDto {

    private Long id;
    private String title;
    private String writer;
    private String content;
    private String createdDate,modifiedDate;
    private int views;
    private Long userId;
    private List<CommentResponseDto> comments;

    // Entity -> Dto
    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.writer = board.getWriter();
        this.content = board.getContent();
        this.createdDate = String.valueOf(board.getCreateDate());
        this.modifiedDate = String.valueOf(board.getModifiedDate());
        this.views = board.getCount();
        this.userId = board.getUser().getId();
        this.comments = board.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
}
