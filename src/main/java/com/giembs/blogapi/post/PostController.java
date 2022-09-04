package com.giembs.blogapi.post;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1")
public class PostController {

    @GetMapping(value="/post/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPosts(@PathVariable Integer postId){
        return new String("The post id is "+postId);
    }

    @GetMapping(value="/post", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPost(){
        return new ResponseEntity<>("Jesus is Lord", HttpStatus.OK);
    }
    
}
