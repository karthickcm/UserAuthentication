package com.km.authentication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.km.authentication.AuthenticationApplication;
import com.km.authentication.entity.UserAuthRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthenticationApplication.class)
public class AuthenticationServiceTest {

	@Autowired
	private AuthenticationService authenticationService;
	
	private UserAuthRequestDto request;
	
	@Before
	public void setUpData() {
		request = new UserAuthRequestDto();
		request.setUserName("karthickcm@gmail.com");
		request.setPassword("Test@123");
	}
	
	@Test
	public void verifyValidUser() throws Exception {
		assertEquals(0, authenticationService.isValidUser(request).responseCode);
		assertEquals("000", authenticationService.isValidUser(request).errorCode);
	}
	
	@Test
	public void verifyInvalidUser() throws Exception {
		request.setUserName("Test");
		assertEquals(-2, authenticationService.isValidUser(request).responseCode);
		assertEquals("INUSER", authenticationService.isValidUser(request).errorCode);
	}
	
	@Test
	public void verifyInvalidPassword() throws Exception {
		request.setPassword("test");
		assertEquals(-2, authenticationService.isValidUser(request).responseCode);
		assertEquals("INPASS", authenticationService.isValidUser(request).errorCode);
	}
	
}
