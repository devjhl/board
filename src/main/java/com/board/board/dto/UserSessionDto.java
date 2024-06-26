package com.board.board.dto;

import com.board.board.domain.user.Role;
import com.board.board.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserSessionDto implements Serializable {

    private Long id;
    private String username;
    private String nickname;
    private String email;
    private Role role;

    // Entity -> dto
    public UserSessionDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
