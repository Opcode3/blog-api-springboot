package com.giembs.blogapi.user;

import java.util.List;

public interface UserService{

    List<UserResponse> fetchAllUsers();
    UserResponse createUser(UserRequest userRequest);
    UserResponse fetchUser(Long id);
    void removeUser(Long id);

    UserResponse updateUser(Long id, UserRequest userRequest);
}
