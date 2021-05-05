package com.km.authentication.dto.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.km.authentication.service.MessageByLocaleService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {
	
	@Autowired
	MessageByLocaleService localResolver;
	
	public int responseCode;
	public String errorCode;
	public String errorDesc;

	
	/*
	public CommonResponse defaultSuccess() {
		return new CommonResponse(0, Constants.success, localResolver.getErrorMessage(Constants.success));
	}
	
	public CommonResponse defaultError() {
		return new CommonResponse(-1, Constants.internalErrorCode, localResolver.getErrorMessage(Constants.internalErrorCode));
	}*/

	@Override
	public String toString() {
		return "CommonResponse [localResolver=" + localResolver + ", responseCode=" + responseCode + ", errorCode="
				+ errorCode + ", errorDesc=" + errorDesc + "]";
	}
}