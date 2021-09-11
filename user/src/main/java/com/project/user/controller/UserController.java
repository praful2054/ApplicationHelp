package com.project.user.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.user.model.Data;
import com.project.user.model.User;
import com.project.user.model.UserData;
import com.project.user.model.UserDataResponse;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/create/{filename}")
	public ResponseEntity<UserDataResponse> createAndGet(@RequestBody Data data, @PathVariable("filename") String name)
			throws ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException,
			KeyStoreException {
		
		UserData userData = new UserData();
		
		
		
		List<User> listOfUser = new ArrayList<>();
		
		for(String userId: data.getUserlist()) {
			User user = new User();
			user.setVin(userId);
			listOfUser.add(user);
			
		}
		userData.setCsv(listOfUser);
		
		

		final String baseUrl = "https://test.net/test1";

		ObjectMapper obj = new ObjectMapper();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("tc-username", "user");

		String bodyData = obj.writeValueAsString(userData);

		HttpEntity<String> httpEntity = new HttpEntity<String>(bodyData, headers);

		ResponseEntity<String> postForEntity = restTemplate.postForEntity(baseUrl + "?name=" + name, httpEntity,
				String.class);

		if (postForEntity.getStatusCodeValue() == 200) {

			ResponseEntity<UserDataResponse[]> getForEntity = restTemplate.getForEntity(baseUrl,
					UserDataResponse[].class);
			
			if (getForEntity.getStatusCodeValue() == 200) {

			UserDataResponse[] listOfData = getForEntity.getBody();

			for (UserDataResponse dataResponse : listOfData) {

				if (dataResponse.getName().equalsIgnoreCase(name)) {
					UserDataResponse desiredData = dataResponse;
					HttpHeaders newHeaders = new HttpHeaders();
					newHeaders.setContentType(MediaType.APPLICATION_JSON);
					newHeaders.set("user", desiredData.getUser());
					HttpEntity<String> newhttpEntity = new HttpEntity<String>(newHeaders);

					ResponseEntity<UserDataResponse> response = restTemplate.exchange(
							baseUrl + "/" + desiredData.getId() + "/submit", HttpMethod.GET, newhttpEntity,
							UserDataResponse.class);

					/*
					 * ResponseEntity<UserDataResponse> finalOutput = restTemplate.getForEntity(
					 * baseUrl + "/" + desiredData.getId() + "/submit", newHeaders,
					 * UserDataResponse.class);
					 */
					return response;
				}

			}
			}

			System.out.println(postForEntity.getBody());
		} else
			System.out.println("Not got Successfull Response");
		return null;

	}

}
