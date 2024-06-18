package com.board.board.service;

import com.board.board.domain.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    User registerUser(User user);

    Optional<User> findByUsername(String username);
}