package com.km.authentication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.km.authentication.model.UserLoginSuccessHistory;

public interface UserLoginSuccessHistoryRepo extends JpaRepository<UserLoginSuccessHistory, String> {
	
}
