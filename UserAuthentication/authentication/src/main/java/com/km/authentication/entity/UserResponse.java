package com.km.authentication.entity;

import com.km.authentication.dto.response.CommonResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse extends CommonResponse {
	
	public Token token;
	
	@Override
	public String toString() {
		return "UserAuthDetailsResponseDto [token=" + token + "]";
	}
}
