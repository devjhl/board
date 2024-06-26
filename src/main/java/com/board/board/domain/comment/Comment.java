package com.board.board.domain.comment;

import com.board.board.domain.board.Board;
import com.board.board.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "comments")
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;
    @Column(name = "created_date")
    @CreatedDate
    private String createdDate;
    @Column(name = "modified_date")
    @LastModifiedDate
    private String modifiedDate;
    @ManyToOne
    @JoinColumn(name = "board_id") //외래키 생성, Board 엔티티의 기본키(id)와 매핑
    private Board board;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
