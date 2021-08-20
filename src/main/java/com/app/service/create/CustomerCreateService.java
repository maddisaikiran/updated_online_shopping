package com.app.service.create;

import com.app.businessException.BusinessException;

public interface CustomerCreateService {
	
public boolean checkName(String customerName) throws BusinessException;
	
	public boolean checkUserName(String customerUsername) throws BusinessException;
	
	public boolean checkEmail(String customerEmail) throws BusinessException;
	
	public boolean checkPassword(String customerPassword) throws BusinessException;

}
