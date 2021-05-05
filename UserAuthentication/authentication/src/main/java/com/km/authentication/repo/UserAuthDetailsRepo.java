package com.km.authentication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.km.authentication.model.UserAuthDetails;

public interface UserAuthDetailsRepo extends JpaRepository<UserAuthDetails, String> {
	
}
