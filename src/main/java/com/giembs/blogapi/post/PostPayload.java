package com.giembs.blogapi.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.giembs.blogapi.user.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PostPayload {
    @JsonProperty(value = "user_id")
    private Long user_id;
    @JsonProperty(value = "post_title",required = true)
    @NotNull
    @NotEmpty
    private String title;
    @JsonProperty(value = "post_content", required = true)
    private String content;
    @JsonProperty(value = "post_image_url")
    private String imageUrl;

    public PostRequest getRequest(){
        return PostRequest.builder()
                .user_id(user_id)
                .title(title)
                .content(content)
                .imageUrl(imageUrl)
                .build();
    }
}
