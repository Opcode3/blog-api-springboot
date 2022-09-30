package com.giembs.blogapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    // get all registered users
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUser(){
        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    //get user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.fetchUser(id), HttpStatus.OK);
    }

    // create a new user
    @PostMapping("/user")
    public ResponseEntity<UserResponse> addUser(@RequestBody @Valid UserJsonPayload userJsonPayload){
        return new ResponseEntity<>(userService.createUser(userJsonPayload.getRequest()), HttpStatus.CREATED);
    }

    // update user profile
    @PutMapping("/user/{id}")
    public ResponseEntity<UserResponse> editUser(@PathVariable("id") Long id, @Valid @RequestBody  UserJsonPayload userJsonPayload){
        return ResponseEntity.ok(userService.updateUser(id, userJsonPayload.getRequest()));
    }
    // delete user by id
    @DeleteMapping("/user/{id}")
    public  ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userService.removeUser(id);
        return ResponseEntity.ok("User has just been deleted!");
    }

    
}
