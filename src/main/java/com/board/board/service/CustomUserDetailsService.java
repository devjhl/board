package com.board.board.service;

import com.board.board.domain.user.User;
import com.board.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service("customUserDetailsService")
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        User customUserDetails = userOptional.get();
        return new org.springframework.security.core.userdetails.User(
                customUserDetails.getUsername(),
                customUserDetails.getPassword(),
                customUserDetails.isEnabled(),
                true, true, true,
                customUserDetails.getAuthorities()  // Assuming getAuthorities() returns Collection<? extends GrantedAuthority>
        );
    }
}
