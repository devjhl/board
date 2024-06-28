package com.board.board.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class SignupUserDto {
    @NotEmpty(message = "아이디를 입력해주세요")
    @Size(min = 5, max = 13, message = "아이디는 5자에서 13자 사이여야 합니다.")
    private String username;

    @NotEmpty(message = "비밀번호를 입력해주세요")
    @Size(min = 8, max = 20, message = "비밀번호는 8자에서 20자 사이여야 합니다.")
    private String password;

    @NotEmpty(message = "닉네임을 입력해주세요")
    @Size(min = 2, max = 10, message = "닉네임은 2자에서 10자 사이여야 합니다.")
    private String nickname;

    @NotEmpty(message = "이메일을 입력해주세요")
    @Email(message = "유효한 이메일 주소를 입력해주세요")
    @Size(min = 5, max = 50, message = "이메일은 5자에서 50자 사이여야 합니다.")
    private String email;
}



