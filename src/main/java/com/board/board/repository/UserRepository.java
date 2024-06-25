package com.board.board.repository;

import com.board.board.domain.user.User; // User 클래스의 경로에 맞게 수정 필요
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    // username을 가진 user를 찾음
    Optional<User> findByUsername(String username);

    // 테이블에 해당 username을 가진 튜플이 존재하는지 확인함
    boolean existsByUsername(String username);

    User findById(Long userId);
}