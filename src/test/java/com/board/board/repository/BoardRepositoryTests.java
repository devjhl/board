package com.board.board.repository;

import com.board.board.domain.board.Board;
import com.board.board.domain.user.Role;
import com.board.board.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class BoardRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = User.builder()
                .username("testUser")
                .password("password")
                .email("testuser@example.com")
                .nickname("testNickname")
                .enabled(true)
                .role(Role.USER) // Role enum 값을 사용
                .build();
        testUser = userRepository.save(testUser); // 객체 저장 후 할당
    }

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Board board = Board.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .user(testUser) // 저장된 객체 사용
                    .build();
            Board result = boardRepository.save(board);
            assertNotNull(result);
            System.out.println("글번호: " + result.getId());
        });
    }
}