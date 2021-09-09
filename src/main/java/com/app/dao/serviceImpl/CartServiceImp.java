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
	public int deleteProductCart(int customerId) throws BusinessException {
		int c = cartDAO.deleteProductCart(customerId);
		return c;
	}


	@Override
	public int addProductCart(int productId, int customerId) throws BusinessException {
		int c = cartDAO.addProductCart(productId,customerId);
		return c;
	}


	@Override
	public List<Cart> getProductCart(int customerId) throws BusinessException {
		List<Cart> cart = new ArrayList<>();
		cart = cartDAO.getProductCart(customerId);
		return cart;
	}

}
