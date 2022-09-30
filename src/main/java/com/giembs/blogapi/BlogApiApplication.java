package com.giembs.blogapi;


import com.giembs.blogapi.post.Post;
import com.giembs.blogapi.post.PostRepository;
import com.giembs.blogapi.user.User;
import com.giembs.blogapi.user.UserNotFoundException;
import com.giembs.blogapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;


@SpringBootApplication
public class BlogApiApplication { //implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BlogApiApplication.class, args);
	}
//
//    @Autowired
//    PostRepository postRepository;
//
//    @Autowired
//    UserRepository userRepository;

//    @Override
//    public void run(String... args) throws Exception {
//        User user = userRepository.findById(1l).orElseThrow( () -> new UserNotFoundException("This user id is not present!"));
//        Post post = Post.builder()
//                .title("Jesus is Lord!")
//                .content("Truly Jesus is coming soon. Let us all repent from every thing that establishes us in the Lord!")
//                .user(user)
//                .likes(1l)
//                .createdAt(Instant.now())
//                .updatedAt(Instant.now())
//                .build();
//        postRepository.save(post);
//    }
}
