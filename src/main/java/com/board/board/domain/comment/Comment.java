package com.board.board.domain.comment;

import com.board.board.domain.board.Board;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nickname;
    @ManyToOne
    @JoinColumn(name = "board_id") //외래키 생성, Board 엔티티의 기본키(id)와 매핑
    private Board board; // 해당 댓글의 부모 게시글
    @Column
    private String username;
    @Column
    private String body; // 댓글 내용



}
