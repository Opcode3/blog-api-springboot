package com.giembs.blogapi.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(value="/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable("postId") Long postId){
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getPosts(){
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping("/{id}/user")
    public ResponseEntity<List<PostResponse>> getUserPost(@PathVariable("id") Long userId){
        return ResponseEntity.ok(postService.getPostByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> editPost(@PathVariable("id") Long id, @Valid @RequestBody PostPayload postPayload){
        return ResponseEntity.ok(postService.updatePostById(id, postPayload.getRequest()));
    }

    @PostMapping
    public ResponseEntity<PostResponse> createtPost(@Valid @RequestBody PostPayload postPayload){
        return ResponseEntity.ok(postService.createPost(postPayload.getRequest()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removePost(@PathVariable("id") Long id){
        postService.deletePost(id);
        return ResponseEntity.ok("Post deletion was successful!");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostResponse> likePost(@PathVariable("id") Long id){
        return ResponseEntity.ok(postService.updateLike(id));
    }



    
}
