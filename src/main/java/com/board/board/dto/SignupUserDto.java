package com.board.board.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class SignupUserDto {
    private String username;
    private String nickname;
    private String password;
    private String email;
    private String role;
}



