package com.app.service.create.impl;

import com.app.businessException.BusinessException;
import com.app.service.create.CustomerCreateService;

public class CustomerCreateServiceImpl implements CustomerCreateService{

	@Override
	public boolean checkName(String customerName) throws BusinessException {
           boolean c = false;
		
		if(customerName.matches("[a-zA-Z]{2,15}")) {
			c = true;
		}
		else {
			throw new BusinessException("Invalid name: "+customerName);
		}
		
		return c;
	}

	@Override
	public boolean checkUserName(String customerUsername) throws BusinessException {
             boolean c = false;
		
		if(customerUsername.matches("[a-zA-Z]{2,15}")) {
			c = true;
		}
		else {
			throw new BusinessException("Invalid user name: "+customerUsername);
		}
		return c;
	}

	@Override
	public boolean checkEmail(String customerEmail) throws BusinessException {
             boolean c = false;
		
		if(customerEmail.matches("[a-zA-Z0-9]{3,15}@[a-z]{3,15}.com")) {
			c = true;
		}
		else {
			throw new BusinessException("Invalid email : "+customerEmail);
		}
		
		return c;
	}

	@Override
	public boolean checkPassword(String customerPassword) throws BusinessException {
boolean c = false;
		
		if(customerPassword.matches("[a-zA-Z]{5,10}")) {
			c = true;
		}
		else {
			throw new BusinessException("Invalid password: "+customerPassword);
		}
		
		return c;
	}

}
