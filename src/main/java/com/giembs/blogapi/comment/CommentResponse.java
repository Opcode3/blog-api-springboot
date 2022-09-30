package com.giembs.blogapi.comment;

import com.giembs.blogapi.post.Post;
import com.giembs.blogapi.user.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentResponse {
    private Long id;
    private String content;
    private Post post;
    private User user;
    private String createdAt;
    private String updatedAt;
}
