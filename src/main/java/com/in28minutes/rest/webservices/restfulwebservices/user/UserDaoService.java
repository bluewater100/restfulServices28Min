package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    
    //I added this just to support retrievAllPosts for doing a Tdd test
    private static List<Post> posts = new ArrayList<>();

    private static int usersCount = 3;

    static {

        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Eve", new Date()));
        users.add(new User(3, "Jack", new Date()));
        
        //I added this just to support retrievAllPosts for doing a Tdd test
        // This is only used to support my userResourceTest
        posts.add(new Post(1, "post1",null));
        posts.add(new Post(2, "post2",null));
        posts.add(new Post(3, "post3",null));
        
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user:users){
            if (user.getId()==id) {
                return user;
            }
        }
        return null;
    }
    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId()==id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }

    public List<Post> retrieveAllPosts() {
    		return posts;
    }
}
