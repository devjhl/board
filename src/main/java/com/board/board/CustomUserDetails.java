package com.board.board;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

@Table(name = "user")
@Entity
public class CustomUserDetails implements UserDetails {
    @Id
    private String username;
    private String password;
    private String email;
    private String nickname;
    private boolean enabled = true; // 기본값을 true로 설정

    // Builder 패턴 또는 생성자를 사용해 인스턴스 생성
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;
        private String password;
        private String email;
        private String nickname;
        private boolean enabled = true;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public CustomUserDetails build() {
            CustomUserDetails userDetails = new CustomUserDetails();
            userDetails.username = this.username;
            userDetails.password = this.password;
            userDetails.email = this.email;
            userDetails.nickname = this.nickname;
            return userDetails;
        }
    }

    // UserDetails 인터페이스의 다른 메서드 구현
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }
}