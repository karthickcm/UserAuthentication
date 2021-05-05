package com.km.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageByLocaleService {
	
	@Autowired
    private MessageSource errorMessage;
	
	
	public String getErrorMessage(String id) {
		return errorMessage.getMessage(id, null, LocaleContextHolder.getLocale());
	}
	
}
