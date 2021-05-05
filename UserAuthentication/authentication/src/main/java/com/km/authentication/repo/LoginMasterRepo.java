package com.km.authentication.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.km.authentication.model.LoginMaster;

public interface LoginMasterRepo extends JpaRepository<LoginMaster, String>{
	
	public Optional<LoginMaster> findByLoginId(String loginId);
	public boolean existsByLoginId(String loginId);
	
}
