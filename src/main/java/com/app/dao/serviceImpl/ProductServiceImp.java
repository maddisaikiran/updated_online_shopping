package com.app.dao.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.app.businessException.BusinessException;
import com.app.dao.ProductDAO;
import com.app.daoImpl.ProductDAOImpl;
import com.app.model.Product;
import com.app.service.ProductService;

public class ProductServiceImp implements ProductService{
	ProductDAO productDAO = new ProductDAOImpl();


	@Override
	public int addProduct(Product product) throws BusinessException {
		int c = 0;
		ProductDAO productDAO = new ProductDAOImpl();
		c = productDAO.addProduct(product);
		return c;
	}

	@Override
	public int deleteProduct(Product product) throws BusinessException {
		return 0;
	}

	@Override
	public List<Product> getAllProducts() throws BusinessException {
		List<Product> productList = new ArrayList<>();
		productList = productDAO.getAllProducts();
		return productList;
	}

	@Override
	public List<Product> getProductByName(String productName) throws BusinessException {
		List<Product> productList = new ArrayList<>();
		productList = productDAO.getProductByName(productName);
		return productList;
	}

	@Override
	public List<Product> getProductByCategory(String productCategory) throws BusinessException {
		List<Product> productList = new ArrayList<>();
		productList = productDAO.getProductByCategory(productCategory);
		return productList;
	}

}
