package com.giembs.blogapi.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;

    UserServiceImpl(UserRepository userRepository){
        this.userRepo = userRepository;
    }

    @Override
    public List<UserResponse> fetchAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(this::convertEntityToUserResponse).collect(Collectors.toList());
    }




    private UserResponse convertEntityToUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .avatar_url(user.getAvatar_url())
                .time(user.getTime())
                .build();
    }


    
}
