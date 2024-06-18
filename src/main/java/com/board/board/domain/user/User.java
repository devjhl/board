package com.board.board.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "아이디를 입력해주세요.")
    @Column(nullable = false,length = 30,unique = true)
    private String username; //아이디
    @NotBlank(message = "닉네임을 입력해주세요.")
    @Column(nullable = false)
    private String nickname; //닉네임
    @NotBlank(message = "패스워드를 입력해주세요.")
    @Column(nullable = false, length = 100)
    private String password;
    @NotBlank(message = "이메일을 입력해주세요.")
    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    private String LoginId;



}
