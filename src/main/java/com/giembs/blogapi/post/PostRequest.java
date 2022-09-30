package com.giembs.blogapi.post;


import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class PostRequest {
    private Long user_id;
    private String title;
    private String content;
    private String imageUrl;
}
