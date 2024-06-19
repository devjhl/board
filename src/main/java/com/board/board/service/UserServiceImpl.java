
/*
package com.board.board.service;

import com.board.board.domain.user.User;
import com.board.board.dto.LoginDto;
import com.board.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service("userServiceImpl") // 빈 이름을 명시적으로 설정
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        User user = userOptional.get();
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true, true, true,
                user.getAuthorities()  // Assuming getAuthorities() returns Collection<? extends GrantedAuthority>
        );
    }

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Long save(LoginDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        user.setNickname("nickname_placeholder"); // 임시 닉네임 설정, 실제 구현에서는 필요에 맞게 수정
        user.setEmail("email_placeholder@example.com"); // 임시 이메일 설정, 실제 구현에서는 필요에 맞게 수정
        user.setEnabled(true);

        return userRepository.save(user).getId();
    }
}*/
