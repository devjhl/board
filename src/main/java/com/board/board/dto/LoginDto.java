package com.board.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;


@Data
public class LoginDto {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String username;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
