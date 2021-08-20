package com.app.service;

import java.util.List;

import com.app.businessException.BusinessException;
import com.app.model.Product;


public interface EmployeeSearchService {
	public Product getProductId(int productid) throws BusinessException;
	public List<Product> getProductPrice(int productPrice) throws BusinessException;
	public List<Product> getProductRating(int productRating) throws BusinessException;
	

}
