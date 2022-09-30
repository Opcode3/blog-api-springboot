package com.giembs.blogapi.post;

import com.giembs.blogapi.user.User;
import com.giembs.blogapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{


    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PostResponse> getPosts() {
        List<Post> all_post = postRepository.findAll();
        return all_post.stream().map(this::convertEntityToResponse).collect(Collectors.toList());
    }

    @Override
    public PostResponse updateLike(Long id){
        Post retrieved_post = postRepository.findById(id).orElseThrow(()-> new RuntimeException("Unable to find post id: "+id));
        retrieved_post.setLikes(retrieved_post.getLikes() + 1);
        Post updated_post = postRepository.save(retrieved_post);
        return convertEntityToResponse(updated_post);
    }

    @Override
    public PostResponse getPost(Long id) {
        Post retrieved_post = postRepository.findById(id).orElseThrow(()-> new PostNotFoundException("Unable to find post id: "+id));
        return convertEntityToResponse(retrieved_post);
    }

    @Override
    public List<PostResponse> getPostByUserId(Long user_id) {
        List<Post> retrieved_posts = postRepository.findAllByUserId(user_id);
        return retrieved_posts.stream().map(this::convertEntityToResponse).collect(Collectors.toList());
    }

    @Override
    public PostResponse updatePostById(Long id, PostRequest postRequest) {
        Post updating_post = postRepository.findById(id).orElseThrow(()-> new RuntimeException("Unable to find post id: "+id));
        updating_post.setTitle(postRequest.getTitle());
        updating_post.setContent(postRequest.getContent());
        updating_post.setImageUrl(postRequest.getImageUrl());
        updating_post.setUpdatedAt(Instant.now());

        Post updated_post = postRepository.save(updating_post);
        return convertEntityToResponse(updated_post);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostResponse createPost(PostRequest postRequest) {

        try{

            User user = userRepository.findById(postRequest.getUser_id()).get();
            Post new_post = Post.builder()
                    .title(postRequest.getTitle())
                    .content(postRequest.getContent())
                    .imageUrl(postRequest.getImageUrl())
                    .likes(0L)
                    .user(user)
                    .createdAt(Instant.now())
                    .updatedAt(Instant.now())
                    .build();
            Post savedPost = postRepository.save(new_post);
            return convertEntityToResponse(savedPost);
        }catch (RuntimeException e){
            throw new RuntimeException("Error: Unable to find user data", e.getCause());
        }

    }

    private PostResponse convertEntityToResponse(Post post){
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .likes(post.getLikes())
                .user(post.getUser())
                .createdAt(LocalDateTime.ofInstant(post.getCreatedAt(), ZoneId.of("Africa/Lagos")).format(DateTimeFormatter.ISO_DATE_TIME))
                .updatedAt(LocalDateTime.ofInstant(post.getUpdatedAt(), ZoneId.of("Africa/Lagos")).format(DateTimeFormatter.ISO_DATE_TIME))
                .build();
    }
}
