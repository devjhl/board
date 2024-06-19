package com.board.board.repository;


import com.board.board.CustomUserDetails;
import com.board.board.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<CustomUserDetails, String> {
    // username을 가진 user를 넣음
    Optional<CustomUserDetails> findByUsername(String username);

    // 테이블에 해당 username을 가진 튜플이 존재하는지 확인함
    boolean existsByUsername(String username);
}
