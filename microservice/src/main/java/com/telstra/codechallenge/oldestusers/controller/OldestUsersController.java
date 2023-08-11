package com.telstra.codechallenge.oldestusers.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import com.telstra.codechallenge.oldestusers.response.OldestUsersResponse;
import com.telstra.codechallenge.oldestusers.service.OldestUsersService;
import com.telstra.codechallenge.oldestusers.exceptions.IllegalArgumentException;
import com.telstra.codechallenge.oldestusers.exceptions.UserNotFoundException;

@RequestMapping("/api")
@RestController
public class OldestUsersController {

	@Autowired
	 private OldestUsersService oldestUsersService;
	
	 private final Logger log= LoggerFactory.getLogger(OldestUsersController.class);
	
	 /**
	   * Returns an array of oldest users.
	   * Taken from <a href="https://api.github.com/search">https://api.github.com/search</a>.
	   * For API documentation see: <a href="https://api.github.com/search/">https://api.github.com/search/</a>.
	   * Param count - count of users to return 
	   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	   *         the oldestUsers List, or with status {@code 404 (Not Found)}.
	 * @throws IllegalArgumentException 
	   */
	 
	 @GetMapping(path = "/oldestUsers/{count}")
	  public ResponseEntity<OldestUsersResponse> OldestUsers(@PathVariable(required = true) Long count) throws Exception{
		 log.info("Request to get the list of Oldest Users with Zero Followers with given list size : {}",count);
		try {
			OldestUsersResponse response=oldestUsersService.getOldestUsers(count);
			if(response != null)
				return ResponseEntity.status(HttpStatus.OK).body(response);
			else
				throw new UserNotFoundException("User Not Found with Zero Followers");
		}
		catch(ResponseStatusException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exception in Http Request");
		}
		catch(HttpClientErrorException ex) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Exception in Http client Request");
		}		
	  }
	
	
	
	
}
