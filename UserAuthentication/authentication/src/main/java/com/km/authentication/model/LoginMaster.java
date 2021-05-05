package com.km.authentication.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginMaster {

	@Id
	@Column(length = 80)
	public String loginId;
	
	@Embedded
	public GlobalEntity globalEntity;
	
	@ManyToOne
	@JoinColumn(name = "systemId")
	public UsersDetailsMaster usersDetailsMaster;
	
}