package com.km.authentication.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PINMaster {

	@Column(length = 150, nullable = false)
	public String password;
	
	@JsonIgnore
	@Column(length = 150, nullable = false)
	public String previousPassword;
	
	@JsonIgnore
	@Column(nullable = false)
	public int incorrectCount;
	
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	public Date lastUpdatedDate;

	
	@Override
	public String toString() {
		return "PINMaster [password=" + password + ", previousPassword=" + previousPassword + ", incorrectCount="
				+ incorrectCount + ", lastUpdatedDate=" + lastUpdatedDate + "]";
	}
}