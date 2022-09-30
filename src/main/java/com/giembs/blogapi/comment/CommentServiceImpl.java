package com.giembs.blogapi.comment;

import com.giembs.blogapi.post.Post;
import com.giembs.blogapi.post.PostRepository;
import com.giembs.blogapi.user.User;
import com.giembs.blogapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<CommentResponse> getCommentsByPostId(Long post_id) {
        List<Comment> comments = commentRepository.findAllByPostId(post_id);
        return comments.stream().map(this::convertEntityToResponse).collect(Collectors.toList());
    }

    @Override
    public List<CommentResponse> getCommentsByUserId(Long user_id) {
        List<Comment> comments = commentRepository.findAllByUserId(user_id);
        return comments.stream().map(this::convertEntityToResponse).collect(Collectors.toList());
    }

    @Override
    public CommentResponse getCommentById(Long id) {
        Comment fetch_comment = commentRepository.findById(id).orElseThrow(()-> new CommentNotFoundException("Error: unable to retrieve comment"));
        return convertEntityToResponse(fetch_comment);
    }

    @Override
    public CommentResponse makeComment(CommentRequest commentRequest) {

        User user = userRepository.findById(commentRequest.getUser()).get();
        Post post = postRepository.findById(commentRequest.getPost()).get();

        Comment new_comment = Comment.builder()
                .content(commentRequest.getContent())
                .user(user)
                .post(post)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
        Comment created_comment = commentRepository.save(new_comment);

        return convertEntityToResponse(created_comment);
    }

    @Override
    public CommentResponse updateComment(Long id, CommentRequest commentRequest) {
        Comment to_be_updated = commentRepository.findById(id).orElseThrow(()-> new RuntimeException("Error: Unable to retrieve comment data!"));
        to_be_updated.setContent(commentRequest.getContent());
        to_be_updated.setUpdatedAt(Instant.now());
        Comment updated_comment = commentRepository.save(to_be_updated);
        return convertEntityToResponse(updated_comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }


    private CommentResponse convertEntityToResponse(Comment comment){
        return CommentResponse.builder()
                .id(comment.getId())
                .user(comment.getUser())
                .post(comment.getPost())
                .content(comment.getContent())
                .createdAt(LocalDateTime.ofInstant(comment.getCreatedAt(), ZoneId.of("Africa/Lagos")).format(DateTimeFormatter.ISO_DATE_TIME))
                .updatedAt(LocalDateTime.ofInstant(comment.getUpdatedAt(), ZoneId.of("Africa/Lagos")).format(DateTimeFormatter.ISO_DATE_TIME))
                .build();
    }
}
