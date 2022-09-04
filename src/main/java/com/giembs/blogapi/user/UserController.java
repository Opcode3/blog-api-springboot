package com.giembs.blogapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    // get all registered users
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUser(){
        return new ResponseEntity<>(userService.fetchAllUsers(), H);
    }

    //get user by id
    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable("id") Long id){
        return "Id: "+id;
    }

    // create a new user

    // update user profile

    // delete user by id
    
}
