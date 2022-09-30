package com.giembs.blogapi.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private String avatar_url;
}
