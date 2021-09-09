package shopping;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import com.app.businessException.BusinessException;
import com.app.dao.CartDAO;
import com.app.dao.CustomerDAO;
import com.app.dao.EmployeeDAO;
import com.app.dao.OrderDAO;
import com.app.dao.ProductDAO;
import com.app.daoImpl.CartDAOImpl;
import com.app.daoImpl.CustomerDAOImp;
import com.app.daoImpl.EmployeeDAOImpl;
import com.app.daoImpl.OrderDAOImpl;
import com.app.daoImpl.ProductDAOImpl;
import com.app.model.Customer;
import com.app.model.Product;

class TestCase {
	
	@Test
	void testAddProduct() throws BusinessException {
		ProductDAO dao = new ProductDAOImpl();
		
		Product product = new Product("AC",56000,4.2,1);
		assertsEquals(0,dao.addProduct(product),"Not valid product details");
	}
	

		@Test
		void testAddCustomer() throws BusinessException {
			CustomerDAO dao = new CustomerDAOImp();
			
			Customer customer = new Customer("kkkk","kkkk","kkkk","kkkkk");
			assertEquals(1,dao.createAccount(customer),"Not valid product details");


		}

	
	@Test
	void testEmployeeLogin() throws BusinessException {
		
		EmployeeDAO dao = new EmployeeDAOImpl();
		assertEquals(true,dao.checkValidCredentials("saikiran", "sai123"),"Not valid Entry");
	}
	
	
	@Test
	void testAddProductCart() throws BusinessException {
		CartDAO dao = new CartDAOImpl();
		assertEquals(1,dao.addProductCart(1,1));
		
	}
	
	@Test
	void testDeleteProductCart() throws BusinessException {
		CartDAO dao = new CartDAOImpl();
		assertEquals(1,dao.deleteProductCart(1));
		
	}
	
	@Test
	void testCreateOrder() throws BusinessException {
		OrderDAO dao = new OrderDAOImpl();
		assertEquals(1, dao.createOrder(26, 28, 42222),"Not Valid Product Details or Customer Id");
	}
	
	private void assertsEquals(int i, int addProduct, String string) {
		// TODO Auto-generated method stub
		
	}
	}


