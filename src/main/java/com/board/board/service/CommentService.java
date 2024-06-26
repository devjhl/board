package com.board.board.service;


import com.board.board.domain.board.Board;
import com.board.board.domain.comment.Comment;
import com.board.board.domain.user.User;
import com.board.board.dto.CommentRequestDto;
import com.board.board.repository.BoardRepository;
import com.board.board.repository.CommentRepository;
import com.board.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    //create
    public Long commentSave(String username, Long id, CommentRequestDto dto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패 해당 게시글이 존재하지 않습니다." + id));

        dto.setUser(user);
        dto.setBoard(board);

        Comment comment = dto.toEntity();
        commentRepository.save(comment);

        return dto.getId();
    }

    @Transactional
    public void update(Long boardId, Long id, CommentRequestDto dto) {
        Comment comment = (Comment) commentRepository.findByBoardIdAndId(boardId, id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " + id));
        comment.update(dto.getComment());
    }

    @Transactional
    public void delete(Long boardId, Long id) {
        Comment comment = (Comment) commentRepository.findByBoardIdAndId(boardId, id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));
        commentRepository.delete(comment);
    }
}
