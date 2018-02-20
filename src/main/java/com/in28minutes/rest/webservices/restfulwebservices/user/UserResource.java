package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("id-"+id);
        }

        return user;
    }

    @GetMapping("/userswithlinks/{id}")
    public Resource<User> retrieveUserWithLinks(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("id-"+id);
        }

        // Originally, this method just returned the User object.  Then we progressed to the Hateos concept
        // which is along with the User info we should return other links the user may be interested in. So,
        // we changed it to return a resource of type user.
        // Hateaos is a rest service concept that provides other links that relate to the infor you are passing back
        // for example, the user is retrieving a specific user but we may be talking to a web app that would like
        // other info like the link to all users.  So we create a resource of type user since we will return the user but
        // addition we are adding the all users link to this resource being returned.  So we are returning a resource which
        // both the data and links. You can add other links to the resource as well if you want
        Resource<User> resource = new Resource<User>(user);

        // retrieveAllUsers is the name of the method in this class that we want to get the link to
        // the "all-users" is just the name we are giving to this link
        ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if (user == null) {
            throw new UserNotFoundException("id-"+id);
        }
    }

    // the location gets returned in the response header
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
