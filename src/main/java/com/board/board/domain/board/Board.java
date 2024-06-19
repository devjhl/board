package com.board.board.domain.board;

import com.board.board.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@ToString(exclude = "user")
@EqualsAndHashCode(exclude = "user")
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

    private int count;

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
    }
}