package com.board.board.service;

import com.board.board.domain.user.User;
import com.board.board.dto.SignupUserDto;
import com.board.board.dto.UserRequestDto;
import com.board.board.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void save(SignupUserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setNickname(userDto.getNickname());
        user.setEmail(userDto.getEmail());

        if (userDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        } else {
            throw new IllegalArgumentException("Password cannot be null");
        }

        userRepository.save(user);
    }


    public User findById(Long userId) {
        return userRepository.findById(userId);
    }

    //유저 수정
    public void modify(UserRequestDto dto) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findById(dto.getId()));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setNickname(dto.getNickname());
            if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(dto.getPassword()));
            }
            user.setEmail(dto.getEmail());
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
        }
    }
    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    // SignupUserDto를 User로 변환하는 메서드
    private User convertToUser(SignupUserDto signupUserDto) {
        User user = new User();
        user.setUsername(signupUserDto.getUsername());
        user.setNickname(signupUserDto.getNickname());
        user.setPassword((passwordEncoder.encode(user.getPassword())));
        user.setEmail(signupUserDto.getEmail());
        return user;
    }
}
