package com.board.board.service;

import com.board.board.domain.user.User;
import com.board.board.dto.UserRequestDto;
import com.board.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Long save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user).getId();
    }


    public User findById(Long userId) {
        return userRepository.findById(userId);
    }

    //유저 수정
    public void modify(UserRequestDto dto) {
        User user = userRepository.findById(dto.toEntity().getId()).o
    }
}
