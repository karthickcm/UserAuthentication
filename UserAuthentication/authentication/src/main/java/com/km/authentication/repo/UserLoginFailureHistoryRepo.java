package com.km.authentication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.km.authentication.model.UserLoginFailureHistory;

public interface UserLoginFailureHistoryRepo extends JpaRepository<UserLoginFailureHistory, String> {
	
}
