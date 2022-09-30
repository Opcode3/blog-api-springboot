package com.giembs.blogapi.post;

import com.giembs.blogapi.user.User;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class PostResponse {
    private Long id;
    private User user;
    private String title;
    private String content;
    private String imageUrl;
    private Long likes;
    private String createdAt;
    private String updatedAt;
}
