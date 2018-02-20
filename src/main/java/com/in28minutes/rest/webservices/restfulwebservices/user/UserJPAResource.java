package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
public class UserJPAResource {

    // this was the example before implementing JPA
    @Autowired
    private UserDaoService service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("jpa/users")
    public List<User> retrieveAllUsers() {

        return userRepository.findAll();
    }

    @GetMapping("jpa/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = userRepository.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("id-"+id);
        }

        return user;
    }

    @DeleteMapping("jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.delete(id);

    }

    // the location gets returned in the response header
    @PostMapping("jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("jpa/users/{id}/posts")
    public List<Post> retrieveUserPosts(@PathVariable int id) {
        User user = userRepository.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("id-"+id);
        }

        return user.getPosts();
    }

    @PostMapping("jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
        User user = userRepository.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("id-"+id);
        }

        post.setUser(user);
        postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
