package com.giembs.blogapi.comment;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class CommentPayload {

    @JsonProperty(value = "comment", required = true)
    @NotNull
    private String content;

    @JsonProperty(value = "post_id")
    private Long postId;

    @JsonProperty(value = "user_id")
    private Long userId;


    public CommentRequest getRequest(){
        return CommentRequest.builder()
                .content(content)
                .post(postId)
                .user(userId)
                .build();
    }
}
