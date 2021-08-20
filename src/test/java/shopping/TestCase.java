package shopping;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.Main;
import com.app.businessException.BusinessException;
import com.app.dao.CustomerDAO;
import com.app.dao.ProductDAO;
import com.app.daoImpl.CustomerDAOImp;
import com.app.daoImpl.ProductDAOImpl;
import com.app.model.Customer;
import com.app.model.Product;

class TestCase {
	
	
	@Test
	void checkChoice(){
		assertEquals(true,Main.checkChoice(2),"not valid");
		assertEquals(false,Main.checkChoice(7),"not valid");
	}

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

	private void assertsEquals(int i, int addProduct, String string) {
		// TODO Auto-generated method stub
		
	}
	}


