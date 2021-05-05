package com.km.authentication.repo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.km.authentication.AuthenticationApplication;
import com.km.authentication.model.GlobalEntity;
import com.km.authentication.model.LoginMaster;
import com.km.authentication.model.PINMaster;
import com.km.authentication.model.UsersDetailsMaster;
import com.km.authentication.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthenticationApplication.class)
public class UserMasterRepoTests {

	@Autowired
	private UserMasterRepo userMasterRepo;
	
	private UsersDetailsMaster usersDetailsMaster;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Before
	public void setUpData() throws ParseException {
		Date defaultDate = new SimpleDateFormat("yyyy/MM/dd").parse(Constants.defaultDate);
		Date currentDate = new Date();
		List<LoginMaster> loginMasterList = new ArrayList<>();
		LoginMaster loginMaster;
		PINMaster pinMaster;
		usersDetailsMaster = new UsersDetailsMaster();
		String userName = "Karthik";
		String emailId = "karthickcm@gmail.com";
		String mobileNo = "9994318030";
		String password = "Test@123";
		
		GlobalEntity globalEntity = new GlobalEntity();
		globalEntity.createdBy = (emailId);
		globalEntity.createdOn = (currentDate);
		globalEntity.modifiedBy = (Constants.EMPTY);
		globalEntity.modifiedOn = (defaultDate);
		
		loginMaster = new LoginMaster();
		loginMaster.loginId = (emailId);
		loginMaster.globalEntity = (globalEntity);
		loginMaster.usersDetailsMaster = (usersDetailsMaster);
		loginMasterList.add(loginMaster);
		
		loginMaster = new LoginMaster();
		loginMaster.loginId = (mobileNo);
		loginMaster.globalEntity = (globalEntity);
		loginMaster.usersDetailsMaster = (usersDetailsMaster);
		loginMasterList.add(loginMaster);
		
		pinMaster = new PINMaster();
		pinMaster.password = (passwordEncoder.encode(password));
		pinMaster.incorrectCount = (0);
		pinMaster.previousPassword = ("");
		pinMaster.lastUpdatedDate = (currentDate);
		
		usersDetailsMaster.globalEntity = (globalEntity);
		usersDetailsMaster.status = (1);
		usersDetailsMaster.loginMaster = (loginMasterList);
		usersDetailsMaster.userName = (userName);
		usersDetailsMaster.pinMaster = (pinMaster);
	}
	
	@Test
	public void registerUser() {
		userMasterRepo.save(usersDetailsMaster);
		Assert.assertNotNull(usersDetailsMaster);
	}
}
