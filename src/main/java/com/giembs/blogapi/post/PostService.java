package com.giembs.blogapi.post;

import java.util.List;

public interface PostService{
    List<PostResponse> getPosts();
    PostResponse getPost(Long id);
    List<PostResponse> getPostByUserId(Long user_id);
    PostResponse updatePostById(Long id, PostRequest postRequest);
    void deletePost(Long id);
    PostResponse createPost(PostRequest postRequest);

    PostResponse updateLike(Long id);



}
