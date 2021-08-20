package com.app.dao;

import java.util.List;

import com.app.businessException.BusinessException;
import com.app.model.Product;


public interface EmployeeSearchDAO {
	public Product getProductId(int productid) throws BusinessException;
	public List<Product> getProductPrice(int productPrice) throws BusinessException;
	public List<Product> getProductRating(int productRating) throws BusinessException;

}
