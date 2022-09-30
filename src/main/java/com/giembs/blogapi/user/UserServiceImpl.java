package com.giembs.blogapi.user;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
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


    public UserResponse createUser(UserRequest userRequest){
        User newUser, createdUser;
        newUser = User.builder()
                .email(userRequest.getEmail())
                .name(userRequest.getName()).avatar_url(userRequest.getAvatar_url())
                .password(userRequest.getPassword()).time(Instant.now()).build();

        createdUser = userRepo.save(newUser);

        return convertEntityToUserResponse(createdUser);
    }

    @Override
    public UserResponse fetchUser(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Error: Trying to access a non-existing data"));
        return convertEntityToUserResponse(user);
    }

    @Override
    public void removeUser(Long id) {
        try {
            userRepo.deleteById(id);
        }catch (EmptyResultDataAccessException exception){
            throw new RuntimeException("Error: Trying to delete a non-existing data");
        }
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User current_user = userRepo.findById(id).orElseThrow(()-> new UserNotFoundException("Error: Trying to access a non-existing data"));
        current_user.setName(userRequest.getName());
        current_user.setEmail(userRequest.getEmail());
        current_user.setAvatar_url(userRequest.getAvatar_url());
        current_user.setPassword(userRequest.getPassword());

        User changed_profile = userRepo.save(current_user);
        return convertEntityToUserResponse(changed_profile);
    }


    private UserResponse convertEntityToUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .avatar_url(user.getAvatar_url())
                .time(LocalDateTime.ofInstant(user.getTime(), ZoneId.of("Africa/Lagos")).format(DateTimeFormatter.ISO_DATE_TIME))
                .build();
    }


    
}
