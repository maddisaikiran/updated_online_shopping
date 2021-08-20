package com.app.service;
import java.util.List;

import com.app.businessException.BusinessException;
import com.app.model.Cart;

public interface CartService {
	public int addProductCart(int customerId,int productId) throws BusinessException;
	public List<Cart> getProductCart(int customerId) throws BusinessException;
	public int deleteProductCart(int customerId) throws BusinessException;
	}


