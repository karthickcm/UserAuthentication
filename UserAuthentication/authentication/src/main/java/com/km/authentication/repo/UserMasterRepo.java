package com.km.authentication.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.km.authentication.model.UsersDetailsMaster;

public interface UserMasterRepo extends JpaRepository<UsersDetailsMaster, String> {
	
	public Optional<UsersDetailsMaster> findById(String systemId);
	
}
