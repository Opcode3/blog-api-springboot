package com.giembs.blogapi.comment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // get all comments by postID
    @GetMapping("/{id}/post")
    ResponseEntity<List<CommentResponse>> fetchCommentByPostId(@PathVariable("id") Long id){
        List<CommentResponse> comments = commentService.getCommentsByPostId(id);
        return ResponseEntity.ok(comments);
    }
    // get all comments by userID

    @GetMapping("/{id}/user")
    ResponseEntity<List<CommentResponse>> fetchCommentByUserId(@PathVariable("id") Long id){
        List<CommentResponse> comments = commentService.getCommentsByUserId(id);
        return ResponseEntity.ok(comments);
    }
    // get comment by ID
    @GetMapping("/{id}")
    ResponseEntity<CommentResponse> fetchCommentById(@PathVariable("id") Long id){
        CommentResponse comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }
    // make comment
    @PostMapping
    ResponseEntity<CommentResponse> fetchCommentByUserId(@Valid @RequestBody CommentPayload commentPayload){
        CommentResponse comment = commentService.makeComment(commentPayload.getRequest());
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
    // update comment
    @PutMapping("{id}")
    ResponseEntity<CommentResponse> fetchCommentByUserId(@PathVariable("id") Long id, @Valid @RequestBody CommentPayload commentPayload){
        CommentResponse comment = commentService.updateComment(id, commentPayload.getRequest());
        return ResponseEntity.ok(comment);
    }
    // delete comment
    @DeleteMapping("{id}")
    ResponseEntity<String> deleteCommentById(@PathVariable("id") Long id){
        commentService.deleteComment(id);
        return ResponseEntity.ok("Comment has been deleted!");
    }


    
}
