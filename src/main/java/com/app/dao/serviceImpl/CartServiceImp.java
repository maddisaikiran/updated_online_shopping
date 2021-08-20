package com.app.dao.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.app.businessException.BusinessException;
import com.app.dao.CartDAO;
import com.app.daoImpl.CartDAOImpl;
import com.app.model.Cart;
import com.app.service.CartService;


public class CartServiceImp implements CartService{
	CartDAO cartDAO = new CartDAOImpl();
	
	@Override
	public int addProductCart(int customerId,int productId) throws BusinessException {
		int c = cartDAO.addProductCart(customerId, productId);
		return c;
	}
	
	
	@Override
	public List<Cart> getProductCart(int customerId) throws BusinessException {
		List<Cart> cart = new ArrayList<>();
		cart = cartDAO.getProductCart(customerId);
		return cart;
	}


	@Override
	public int deleteProductCart(int customerId) throws BusinessException {
		int c = cartDAO.deleteProductCart(customerId);
		return c;
	}

}
