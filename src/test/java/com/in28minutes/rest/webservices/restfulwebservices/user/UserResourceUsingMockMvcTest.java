package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@WebMvcTest(UserResource.class)
public class UserResourceUsingMockMvcTest {
	
//    @Mock
//    private UserDaoService userDaoService;
//
//    @InjectMocks
//    private UserResource userResource;
	
	
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDaoService userDaoService;

	
    @Test
    public void testRetrieveUser() throws Exception {
    	
    		//Note: The data for this test is loaded in the static block in UserDaoService 
    		
    		User mockUser = new User(20, "Albert",new Date());

        when(userDaoService.findOne(anyInt())).thenReturn(mockUser);
        
        
        MvcResult mvcResult = mockMvc.perform(get("/users/20"))
                .andExpect( status().isOk())
                .andReturn();
        
        String returnedJson = mvcResult.getResponse().getContentAsString();

        assertTrue(returnedJson.contains("Albert"));
         
        String expected = "{id:20,name:Albert}";
        
        // The false means strict is off since we are not checking for every field
		JSONAssert.assertEquals(expected, mvcResult.getResponse()	.getContentAsString(), false);

    }
    
}
