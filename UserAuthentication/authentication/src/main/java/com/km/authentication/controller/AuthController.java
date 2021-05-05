package com.km.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.km.authentication.entity.UserAuthRequestDto;
import com.km.authentication.entity.UserResponse;
import com.km.authentication.service.AuthenticationService;


/**
 * Created On : 04-May-2021
 * Description : Controller is created for Users service 
 * @author Karthik Muthuraja
 *
 */
@RestController
@RequestMapping(value = "/")
public class AuthController {

	@Autowired
	private AuthenticationService authenticationService;
	
	/**
	 * @apiNote  To display the Welcome Home Page
	 * @return
	 */
	@GetMapping(value = "/")
	public String home() {
		return "Welcome to the User Authentication Service";
	}
	
	/**
	 * @apiNote To validate the user and generate the token if user is valid
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/user/authenticate",
			consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public UserResponse authenticate(UserAuthRequestDto request) throws Exception {
		return authenticationService.isValidUser(request);
	}
	
}
