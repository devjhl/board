package com.board.board.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Getter
@Setter
@Entity
@ToString
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // Role 필드 추가

    private boolean enabled;


    @Builder
    public User(String username, String password, String nickname, String email, Role role,boolean enabled) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.enabled = enabled;
        this.email = email;
        this.role = role != null ? role : Role.USER;
    }

    public User() {
        this.role = Role.USER;
    }

    @Override // 권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority(role.getRole()));
    }

    // 사용자의 id를 반환(고유한 값)
    @Override
    public String getUsername(){
        return username;
    }
    // 사용자 패스워드 반환
    @Override
    public String getPassword(){
        return password;
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }
    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }
    // 패스워드 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }
    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled(){
        return true;
    }
}