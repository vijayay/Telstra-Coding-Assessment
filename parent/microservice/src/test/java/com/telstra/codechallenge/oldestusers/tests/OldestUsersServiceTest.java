package com.telstra.codechallenge.oldestusers.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.telstra.codechallenge.oldestusers.response.OldestUsersResponse;
import com.telstra.codechallenge.oldestusers.response.OldestUsersResponse.Items;
import com.telstra.codechallenge.oldestusers.service.OldestUsersService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(SpringRunner.class)
public class OldestUsersServiceTest {

	@Mock
	RestTemplate restTemplate;
	
	@Mock
	OldestUsersResponse oldUsers;
	
	@InjectMocks
	OldestUsersService oldestUsersService;
	
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
	public void getOldestUsersTest() throws Exception{
		log.info("Validating the Service Request to get Oldest Users with Zero Followers");
		
    	Mockito.when(restTemplate.getForObject(anyString(),any())).thenReturn(oldUsers);
		OldestUsersResponse responseList=  oldestUsersService.getOldestUsers(2L);
		Assertions.assertTrue(responseList.getItems().length>0);
	
	}	
}