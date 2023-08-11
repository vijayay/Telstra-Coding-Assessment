package com.telstra.codechallenge.oldestusers.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.telstra.codechallenge.oldestusers.controller.OldestUsersController;
import com.telstra.codechallenge.oldestusers.response.OldestUsersResponse;
import com.telstra.codechallenge.oldestusers.response.OldestUsersResponse.Items;
import com.telstra.codechallenge.oldestusers.service.OldestUsersService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class OldestUsersControllerTest {

	@Autowired
	private MockMvc restMockMvc;
	
	@InjectMocks
	OldestUsersResponse oldUsers;
	
	@Mock
	OldestUsersController  oldestUsersController;
	
	@MockBean
	private OldestUsersService oldestUsersService;
	
	 private final Logger log= LoggerFactory.getLogger(OldestUsersControllerTest.class);
	
	 public OldestUsersResponse createUsers() {
		 
		Items item1= new OldestUsersResponse().new Items(150,"mattetti","https://github.com/mattetti");
		Items item2= new OldestUsersResponse().new Items(160,"gettelli","https://github.com/gettelli");
		 Items[] items= {item1,item2};
		 OldestUsersResponse users= new OldestUsersResponse();
		 users.setItems(items);
			return users;		
		}
		
		@BeforeEach
		public void initTest() {
			oldUsers=createUsers();
		}
	 
	@Test
	public void OldestUsersTest() throws Exception{
		 log.debug("Validating the Controller Request to get the list of Oldest Users with Zero Followers with given list size");
		 
		 Mockito.when(oldestUsersService.getOldestUsers(2L)).thenReturn(oldUsers);
		restMockMvc.perform(get("/api/oldestUsers/{count}",2L))
		.andExpect(status().isOk());
// testing UserNotFoundException		
		restMockMvc.perform(get("/api/oldestUsers/{count}",0L))
		.andExpect(status().isOk());
		
	}	
}