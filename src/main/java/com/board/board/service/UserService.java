package com.board.board.service;

import com.board.board.domain.user.User;
import com.board.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
}

//    @Override
//    @Transactional
//    public void createUser(UserDetails user) {
//        if (userExists(user.getUsername())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//
//        try {
//            User customUserDetails = User.builder()
//                    .username(user.getUsername())
//                    .password(passwordEncoder.encode(user.getPassword()))
//                    .email(((User) user).getEmail())
//                    .nickname(((User) user).getNickname())
//                    .build();
//            userRepository.save(customUserDetails);
//        } catch (ClassCastException e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @Override
//    public void updateUser(UserDetails user) {
//        // 생략
//    }
//
//    @Override
//    public void deleteUser(String username) {
//        // 생략
//    }
//
//    @Override
//    public void changePassword(String oldPassword, String newPassword) {
//        // 생략
//    }
//
//    @Override
//    public boolean userExists(String username) {
//        return userRepository.existsByUsername(username);
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> userOptional = userRepository.findByUsername(username);
//        if (!userOptional.isPresent()) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//
//        User user = userOptional.get();
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                user.isEnabled(),
//                true, true, true,
//                user.getAuthorities()
//        );
//    }
//}