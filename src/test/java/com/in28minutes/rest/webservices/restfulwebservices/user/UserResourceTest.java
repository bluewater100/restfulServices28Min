package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserResourceTest {
	
    @Mock
    private UserDaoService userDaoService;

    @InjectMocks
    private UserResource userResource;

	
    @Test
    public void testRetrieveUser() throws Exception {
    	   
    		User mockUser = new User(20, "Albert",new Date());

        when(userDaoService.findOne(anyInt())).thenReturn(mockUser);
        
        User returnedUser = userResource.retrieveUser(1);
        
        assertEquals("Albert", returnedUser.getName());
        assertNotNull(returnedUser.getBirthDate());
        verify(userDaoService, times(1)).findOne(anyInt());



    }
    
    @Test
    public void testRetrieveAllPosts() throws Exception {
    	
	    	List<Post> posts = new ArrayList<>();
	    	Post post1 = new Post();
	    	post1.setId(100);
	    	post1.setDescription("Description for Post100");
	    	posts.add(post1);
	    	Post post2 = new Post();
	    	post2.setId(200);
	    	post2.setDescription("Description for Post200");
	    	posts.add(post2);
	    	
	    	when(userDaoService.retrieveAllPosts()).thenReturn(posts);
	    	
	    	List<Post> returnedPosts = userResource.retrieveAllPosts();
	    	
	    	//Junit 4 tests
	    	assertNotNull(returnedPosts);
	    	assertEquals(Integer.valueOf(100), returnedPosts.get(0).getId());
	    	assertEquals("Description for Post200", returnedPosts.get(1).getDescription());
	    	
	    	//Hamcrest tests
	    	assertThat(returnedPosts, not(IsEmptyCollection.empty()));
	    	assertThat(Integer.valueOf(100), is(equalTo(returnedPosts.get(0).getId())));
	    	assertThat("Description for Post200", equalTo(returnedPosts.get(1).getDescription()));
	    	assertThat(returnedPosts, hasSize(2));

    }


}
