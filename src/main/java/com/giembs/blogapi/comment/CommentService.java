package com.giembs.blogapi.comment;

import java.util.List;

public interface CommentService {
    List<CommentResponse> getCommentsByPostId(Long post_id);
    List<CommentResponse> getCommentsByUserId(Long user_id);
    CommentResponse getCommentById(Long id);
    CommentResponse makeComment(CommentRequest commentRequest);
    CommentResponse updateComment(Long id, CommentRequest commentRequest);
    void deleteComment(Long id);
}
