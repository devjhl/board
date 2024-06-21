package com.board.board.domain.board;

import com.board.board.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@ToString(exclude = "users")
@EqualsAndHashCode(exclude = "users")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(updatable = false)
    private LocalDateTime createDate;
    @Column(columnDefinition = "integer default 0", nullable = false)
    private int count;

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
    }

    @Builder
    public Board(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.createDate = LocalDateTime.now();
        count++;
    }

    public String getFormattedCreateDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return this.createDate.format(formatter);
    }
}