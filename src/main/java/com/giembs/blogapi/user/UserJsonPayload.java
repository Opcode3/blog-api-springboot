package com.giembs.blogapi.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserJsonPayload {

    @JsonProperty(value = "full_name")
    @NotNull
    @NotEmpty
    private String name;

    @Email
    private String email;

    @JsonProperty(value = "user_image_url")
    @NotNull
    @NotEmpty
    private String avatar_url;

    @JsonProperty(value = "user_password")
    @NotNull
    private String password;


    public UserRequest getRequest(){
        return UserRequest.builder()
                .name(name)
                .email(email)
                .avatar_url(avatar_url)
                .password(password)
                .build();
    }
}
