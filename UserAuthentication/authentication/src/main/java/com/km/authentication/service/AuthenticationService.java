package com.km.authentication.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.km.authentication.entity.Token;
import com.km.authentication.entity.User;
import com.km.authentication.entity.UserAuthRequestDto;
import com.km.authentication.entity.UserResponse;
import com.km.authentication.model.GlobalEntity;
import com.km.authentication.model.LoginMaster;
import com.km.authentication.model.PINMaster;
import com.km.authentication.model.UserAuthDetails;
import com.km.authentication.model.UserLoginFailureHistory;
import com.km.authentication.model.UserLoginSuccessHistory;
import com.km.authentication.model.UsersDetailsMaster;
import com.km.authentication.repo.LoginMasterRepo;
import com.km.authentication.repo.UserAuthDetailsRepo;
import com.km.authentication.repo.UserLoginFailureHistoryRepo;
import com.km.authentication.repo.UserLoginSuccessHistoryRepo;
import com.km.authentication.util.Constants;


@Service
@Transactional
public class AuthenticationService {
	
	@Autowired
	private LoginMasterRepo loginMasterRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MessageByLocaleService localResolver;
	
	@Autowired
	private UserAuthDetailsRepo userAuthDetailsRepo;
	
	@Autowired
	private UserLoginSuccessHistoryRepo userLoginSuccessHistoryRepo; 
	
	@Autowired
	private UserLoginFailureHistoryRepo userLoginFailureHistoryRepo;
	
	
	public UserResponse isValidUser(UserAuthRequestDto user) throws Exception {
		UserResponse userResponse = new UserResponse();
		User request = new User();
		request.userName = user.getUserName();
		request.password = user.getPassword();
		Date defaultDate = new SimpleDateFormat("yyyy/MM/dd").parse(Constants.defaultDate);
		Date currentDate = new Date();
		
		System.out.println( "request userName == "+request.userName);
		
		// Check User is available in Database
		if (loginMasterRepo.findByLoginId(request.userName).isPresent()) {
			System.out.println("INSIDE USER");
			LoginMaster login = loginMasterRepo.findByLoginId(request.userName).get();
			String encodedPassword = login.usersDetailsMaster.pinMaster.password;
			if(passwordEncoder.matches(request.password, encodedPassword)) {
				
				GlobalEntity globalEntity = new GlobalEntity();
				globalEntity.createdBy = request.userName;
				globalEntity.createdOn = currentDate;
				globalEntity.modifiedBy = Constants.EMPTY;
				globalEntity.modifiedOn = defaultDate;
				
				// User is valid generate and store token in database
				UserAuthDetails userAuthDetails = new UserAuthDetails();
				userAuthDetails.status = 1;
				userAuthDetails.systemId = login.usersDetailsMaster.systemId;
				userAuthDetails.globalEntity = globalEntity;
				
				userAuthDetailsRepo.save(userAuthDetails);
				
				Token token = new Token();
				token.authToken = userAuthDetails.token;
				
				// User is valid and put success entry
				UserLoginSuccessHistory userLoginSuccessHistory = new UserLoginSuccessHistory();
				userLoginSuccessHistory.loginDate = (currentDate);
				userLoginSuccessHistory.systemId = login.usersDetailsMaster.systemId;
				userLoginSuccessHistory.userName = (login.usersDetailsMaster.userName);
				userLoginSuccessHistoryRepo.save(userLoginSuccessHistory);
				
				userResponse.responseCode = Constants.SUCCESS_RESPONSE_CODE;
				userResponse.errorCode = Constants.success;
				userResponse.errorDesc = localResolver.getErrorMessage(Constants.success);
				userResponse.token = token;
			} else {
				// Valid user but Password is incorrect put entry in Database
				UsersDetailsMaster userMaster = login.usersDetailsMaster;
				PINMaster pinMaster = userMaster.pinMaster;
				userMaster.pinMaster.incorrectCount = (pinMaster.incorrectCount + 1);
				
				UserLoginFailureHistory userLoginFailureHistory = new UserLoginFailureHistory();
				userLoginFailureHistory.loginDate = (currentDate);
				userLoginFailureHistory.systemId = (userMaster.systemId);
				userLoginFailureHistory.errorCode = (Constants.INVALID_PASSWORD);
				userLoginFailureHistory.errorDesc = (localResolver.getErrorMessage(Constants.INVALID_PASSWORD));
				userLoginFailureHistory.userName = (userMaster.userName);
				
				userLoginFailureHistoryRepo.save(userLoginFailureHistory);
				userResponse.responseCode = (Constants.VALIDATION_ERROR_RESPONSE_CODE);
				userResponse.errorCode = (Constants.INVALID_PASSWORD);
				userResponse.errorDesc = (localResolver.getErrorMessage(Constants.INVALID_PASSWORD));
				userResponse.token = new Token();
			}
		} else { // else User is not available in Database
			userResponse.responseCode = Constants.VALIDATION_ERROR_RESPONSE_CODE;
			userResponse.errorCode = Constants.INVALID_USER;
			userResponse.errorDesc = localResolver.getErrorMessage(Constants.INVALID_USER);
			userResponse.token = new Token();
		}
		return userResponse;
	}
	
}
