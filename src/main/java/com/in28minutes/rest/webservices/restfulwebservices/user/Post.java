package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer id;
    private String description;

    // Many posts can be associated with one user
    // We user Json Ignore so that when we retrieve all posts for a user we only get
    // the post info and not the user info.  Otherwise we would get into a recursive scenario
    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "description='" + description + '\'' +
                '}';
    }
}
