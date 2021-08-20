package com.app.dao.serviceImpl;

import java.util.List;

import com.app.businessException.BusinessException;
import com.app.dao.CustomerDAO;
import com.app.daoImpl.CustomerDAOImp;
import com.app.model.Customer;
import com.app.service.CustomerService;

public class CustomerServiceImp implements CustomerService {

	CustomerDAO customerDAO = new CustomerDAOImp();

	@Override
	public int createAccount(Customer customer) throws BusinessException {
		int c = 0;

		CustomerDAO customerDAO = new CustomerDAOImp();
		c = customerDAO.createAccount(customer);

		return c;
	}

	@Override
	public boolean checkValidCredentials(String username, String password) throws BusinessException {
		boolean login = false;
		if (username.length() < 15 || password.length() < 15) {
			login = customerDAO.checkValidCredentials(username, password);		
		} else {
			throw new BusinessException("Please enter valid Username and Password!");
		}
		return login;
	}

	@Override
	public Customer getCustomerByCustomerId(int customerId) throws BusinessException {
		Customer customer = null;
		customer = customerDAO.getCustomerByCustomerId(customerId);
		return customer;
	}

	@Override
	public List<Customer> getCustomerByName(String customerName) throws BusinessException {
		List<Customer> customerList = null;
		customerList = customerDAO.getCustomerByName(customerName);
		return customerList;
	}

	@Override
	public Customer getCustomerByEmail(String customerEmail) throws BusinessException {
		Customer customer = null;
		customer = customerDAO.getCustomerByEmail(customerEmail);
		return customer;
	}

	


}
