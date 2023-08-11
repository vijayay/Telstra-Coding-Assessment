package com.telstra.codechallenge.oldestusers.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.telstra.codechallenge.oldestusers.response.OldestUsersResponse;

@Service
public class OldestUsersService {

	private final Logger log = LoggerFactory.getLogger(OldestUsersService.class);

	/**
	 * Returns an array of oldest users with Zero Followers. * Taken from
	 * <a href="https://api.github.com/search">https://api.github.com/search</a>.
	 * For API documentation see:
	 * <a href="https://api.github.com/search/">https://api.github.com/search/</a>.
	 * Param numberOfRecords - count of users to return
	 * 
	 * @return - a oldest users list.
	 */
	public OldestUsersResponse getOldestUsers(Long numberOfRecords) {
		log.debug("Request to get Oldest Users with Zero Followers : {}", numberOfRecords);
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://api.github.com/search/users?q=followers:0&sort=joined&order=asc";

		if (numberOfRecords > 0) {
			uri = uri + "&per_page=" + numberOfRecords;
		}
		String response = restTemplate.getForObject(uri, String.class);

		Gson g = new Gson();
		OldestUsersResponse users = g.fromJson(response, OldestUsersResponse.class);

		return users;
	}
}