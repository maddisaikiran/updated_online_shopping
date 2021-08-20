package com.app.dao.serviceImpl;

import java.util.List;

import com.app.businessException.BusinessException;
import com.app.dao.EmployeeSearchDAO;
import com.app.daoImpl.EmployeeSearchDAOImpl;
import com.app.model.Product;
import com.app.service.EmployeeSearchService;



public class EmployeeSearchServiceImp implements EmployeeSearchService {
	private EmployeeSearchDAO employeeSearchDAO=new EmployeeSearchDAOImpl();
	@Override
	public Product getProductId(int productid) throws BusinessException {
		Product product=null;
		if(productid <= 0) {
			throw new BusinessException("Invalid Player Id "+productid);
		}else {
			//code here to DAO
			product=employeeSearchDAO.getProductId(productid);
			
		}
		return product;
	}

	@Override
	public List<Product> getProductPrice(int productPrice) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductRating(int productRating) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
