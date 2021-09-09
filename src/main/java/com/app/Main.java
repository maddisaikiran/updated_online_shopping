package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.businessException.BusinessException;
import com.app.dao.CustomerDAO;
import com.app.dao.serviceImpl.CartServiceImp;
import com.app.dao.serviceImpl.CustomerServiceImp;
import com.app.dao.serviceImpl.EmployeeSearchServiceImp;
import com.app.dao.serviceImpl.EmployeeServiceImp;
import com.app.dao.serviceImpl.OrderServiceImpl;
import com.app.dao.serviceImpl.ProductServiceImp;
import com.app.daoImpl.CustomerDAOImp;
import com.app.model.Cart;
import com.app.model.Customer;
import com.app.model.Order;
import com.app.model.Product;
import com.app.service.CartService;
import com.app.service.CustomerService;
import com.app.service.EmployeeSearchService;
import com.app.service.EmployeeService;
import com.app.service.OrderService;
import com.app.service.ProductService;
import com.app.service.create.CustomerCreateService;
import com.app.service.create.impl.CustomerCreateServiceImpl;


public class Main {

	
	static Scanner scanner = new Scanner(System.in);
	static Logger log = Logger.getLogger(Main.class);
	static ProductService productService = new ProductServiceImp();
	static CartService cartService = new CartServiceImp();
	static OrderService orderService = new OrderServiceImpl();
	static CustomerService customerService = new CustomerServiceImp();
	static Customer customer = new Customer();
	static EmployeeService employeeLogin = new EmployeeServiceImp();
	
	
	
	
	public static void searchCustomers() {
		
		int choice = 0;
		do {
			log.info("\n Welcome to Search Customers ");
			log.info("1)Search by Customer Id");
			log.info("2)Search by Name");
			log.info("3)Search by Email");
			log.info("4)Previous Menu");
			log.info("Enter your choice-->");
			try {
				choice = Integer.parseInt(scanner.nextLine());
			switch(choice) {
			case 1:
				log.info("Enter Customer Id ");
				int customerId = Integer.parseInt(scanner.nextLine());
				
				Customer result1 = customerService.getCustomerByCustomerId(customerId);
				
				if(result1!= null) {
					log.info(result1);
				}
				break;
			case 2:
				log.info("Enter Customer Name ");
				String customerName = scanner.nextLine();
				
				List<Customer> customerList = customerService.getCustomerByName(customerName);
				
				if(customerList.size()>0) {
					for (Customer customer : customerList) {
						log.info(customer);
					}
				}
				break;
			case 3:
				log.info("Enter Customer Email-Id ");			
				String email = scanner.nextLine();
				
				Customer result3 = customerService.getCustomerByEmail(email);
				
				if(result3!= null) {
					log.info(result3);
				}
				break;
			case 4:
				log.info(" Going to previous menu ");
				break;
			default:
				log.warn("Please enter valid choice (1-5)\n");
				
			}
			} catch (NumberFormatException e) {
				log.info("Entry is not appropriate. Please Enter Valid Choice\n");
				//continue;
			} catch (BusinessException e) {
				log.warn(e.getMessage());
			}
		}while(choice!=4);
	}
	
	
public static void searchProducts() {
	int choice = 0;
	do {
		log.info("Welcome to search a product)");
		log.info("1)By  Product Name");
		log.info("2)By Product Category");
		log.info("3)Go to Previous Menu");
		log.info("Enter your choice-->");
		try {
			choice = Integer.parseInt(scanner.nextLine());
		switch(choice) {
		case 1:
			log.info("Enter Product Name ");
			String productName = scanner.nextLine();
			List<Product> productListName = new ArrayList<>();
			
				productListName = productService.getProductByName(productName);
				for (Product product : productListName) {
					log.info(product);
				}
			break;
		case 2:
			log.info("Enter Product Category id");
			String productCategory = scanner.nextLine();
			List<Product> productListByCategory = new ArrayList<>();
			
			productListByCategory = productService.getProductByCategory(productCategory);
			for (Product product : productListByCategory) {
				log.info(product);
			}
			break;
		case 3:
			log.info(" Going to Previous Menu ");
			break;
		default:
			log.warn("Please enter valid choice (1-3)\n");
			}
		} catch (NumberFormatException e) {
			log.info("Entry is not appropriate. Please Enter Valid Choice\n");
			continue;
		}catch(BusinessException e) {
			 log.info(e.getMessage());
			 continue;
		 }
		}while(choice!=3);
}

public static void addProducts() {
	
	int productCategoryId = 0;
	double productPrice = 0.0d;
	double productRating = 0.0d;
	
	log.info("Enter Product Name ::");
	String productName = scanner.nextLine();
	log.info("Enter Product Price ::");
	try {
		productPrice = Double.parseDouble(scanner.nextLine());
		log.info("Enter Product rating ::");
		productRating = Double.parseDouble(scanner.nextLine());
		log.info("Enter Product Category Id ::");
		productCategoryId = Integer.parseInt(scanner.nextLine());
	} catch (NumberFormatException e) {
		log.info("Entry is not appropriate. Please Enter Valid Input\n");
	}
	Product product = new Product(productName, productPrice, productRating,productCategoryId);

	try {
		int c = productService.addProduct(product);
		if (c == 1) {
			log.info("Product add Successfully!!!!!!!!!!");
		}
	} catch (BusinessException e) {
		log.warn(e.getMessage());
	}
}


public static void viewAllProducts() {
	
	log.info("Products Details are Below");											
	List<Product> productList;
	try {
		productList = productService.getAllProducts();
		for (Product product : productList) {
			log.info(product);
		}
	     log.info("\n");
	} catch (BusinessException e) {
		log.info(e.getMessage());
	}
	int choice = 0;
	do {
		log.info("*******************************************");
		log.info("1)Add any product to Cart");
		log.info("2)Previous Menu");

		try {
			choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
			case 1:
				log.info("Enter Product Id to add it to cart-->");
				int productId = Integer.parseInt(scanner.nextLine());
				int customerId = customer.getCustomerId();

				if (cartService.addProductCart(productId, customerId) == 1)
					log.info("Product " + productId + " added successfully to cart!!!\n");
				break;
			case 2:
				log.info("***Going to Previous Menu***");
				break;
			default:
				log.warn("Please enter valid choice (1-2)\n");
			}
		} catch (NumberFormatException e) {
			log.info("Entry is not appropriate. Please Enter Valid Choice\n");
		} catch (BusinessException e) {
			log.info(e.getMessage());
		}
	} while (choice != 2);
	
	
}


public static void viewCart() {
	
	try {
		List<Cart> cartList = cartService.getProductCart(customer.getCustomerId());
		log.info("There are "+ cartList.size() +" product in Cart");
		
		double totalPrice = 0.0d;
		for (Cart cart : cartList) {
			log.info(cart);
			totalPrice+= cart.getPrice();
		}
			log.info("\nTotal Price is "+totalPrice);
		
		int chance = 0;
		
		do {
		log.info("\n");
		log.info("place order to products");
		log.info("1)place the order");
		log.info("2)Going to previous");
		
		try {
			chance = Integer.parseInt(scanner.nextLine());
		switch(chance) {
		case 1:
			int customerId = customer.getCustomerId();
			int result = 0;
			for (Cart cart : cartList) {
				int productId = cart.getProductId();
				double productPrice = cart.getPrice();
				result = orderService.createOrder(customerId, productId, productPrice);
			}
			if(result == 1) {
				if(cartService.deleteProductCart(customer.getCustomerId())==1) {
					log.info("Ordered Placed successfully.");
				}
			}
			break;
		case 2:
			log.info("***Going to Previous menu***");
			break;
		default:
			log.warn("Please enter valid choice (1-2)\n");
		}
		}catch (NumberFormatException e) {
			log.info("Entry is not appropriate. Please Enter Valid Choice\n");
			continue;
		}catch(BusinessException e) {
			 log.info(e.getMessage());
			 continue;
		 }
		}while(chance != 1 && chance != 2);
		
		}
		catch (BusinessException e) {
			log.info(e.getMessage());
		}
}

public static void customerSignup() {
	CustomerDAO customerDAO = new CustomerDAOImp();
	CustomerCreateService customerCreateService = new CustomerCreateServiceImpl();
	

	log.info("\n**** Welcome to Signup ****");
	log.info("Enter Name-->");
	String customerName = scanner.nextLine();
	try {
		if(customerCreateService.checkName(customerName)) {
			customer.setCustomerName(customerName);
		}
		 
	}catch (BusinessException e) {
           log.warn(e.getMessage());
         
	}
	log.info("Enter Username-->");
	String customerUsername = scanner.nextLine();
	try {
		if(customerCreateService.checkUserName(customerUsername)) {
			customer.setCustomerUsername(customerUsername);
		}
	}catch (BusinessException e) {
		 log.warn(e.getMessage());
         //continue;
	}
	log.info("Enter Email-Id-->");
	String customerEmail = scanner.nextLine();
	try {
		if(customerCreateService.checkEmail(customerEmail)) {
			customer.setCustomerEmail(customerEmail);
		}
	}catch (BusinessException e) {
		log.warn(e.getMessage());
        //continue;
	}
	log.info("Enter Password-->");
	String customerPassword = scanner.nextLine();
	try {
		if(customerCreateService.checkPassword(customerPassword)) {
			customer.setCustomerPassword(customerPassword);
		}
	}catch (BusinessException e) {
		log.warn(e.getMessage());
        //continue;
	}
	

	//Customer customer = new Customer(customerName, customerUsername, customerPassword,
			//customerEmail);
	try {
		int c = customerDAO.createAccount(customer);
		if (c == 1) {
			log.info("Account create Successfully");
		}
	} catch (BusinessException e) {
		log.warn(e.getMessage());
	}
	
}

public static void viewProducts() {
	log.info("Products Details are Below");											

	try {
		List<Product> 	productList = productService.getAllProducts();
		for (Product product : productList) {
			log.info(product);
		}
	
	} catch (BusinessException e) {
		log.info(e.getMessage());
	}
	
}

public static void employeeLogin() {
	int emp = 0;
	do {

		log.info("\n   Welcome to Employee Login ");
		log.info("Enter your username");
		String username = scanner.nextLine();
		log.info("Enter your password");
		String password = scanner.nextLine();

		try {
			boolean valid = employeeLogin.checkValidCredentials(username, password);
			if (valid) {				
				
				log.info("Login Successfully!!!!!!!");
				log.info("Welcome " + username + ",What you wanna do today?\n");
				int choice = 0;
				do {
					log.info("1)add Products");
					log.info("2)View Products");
					log.info("3)Search Products By filter");
					log.info("4)Search Customer By filter");
					log.info("5)Mark the Status of Order Shipped!");
					log.info("6)LogOut");
					log.info("Enter your valid choice between 1-6");
					try {
						choice = Integer.parseInt(scanner.nextLine());
					
					switch (choice) {
					case 1:
						addProducts();
						break;
					case 2:
						viewProducts();
						break;
					case 3:
						searchProducts();
						break;
					case 4:
						searchCustomers();
						break;
					case 5:
						markOrder(1, "Shipped");
						break;
					case 6:
						log.info("Logout Successfully");
						emp = 5;
						break;
					default:
						log.warn("Please enter valid choice (1-5)\n");
					}

				} catch (NumberFormatException e) {
					log.info("Entry is not appropriate. Please Enter Valid Choice\n");
					continue;
				}
				}while (choice != 6);
			}
		} catch (BusinessException e) {
			log.info(e.getMessage());
			emp++;
			if (emp > 0)
				log.info("\nRemain chance to try login again is " + (5 - emp) + "\n");
		}
	} while (emp < 5);
}

public static void employeesPortal() {
	int choice = 0;
	do {
		log.info("**********************************");
		log.info("\n**** Welcome to Employee Login Portal****");
		log.info("1) login");
		log.info("2) Go to main menu");
		try {
			choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
			case 1:
				employeeLogin();
				break;
			case 2:
				log.info("*****Going to main menu******");
				break;
			default:
				log.warn("Please enter valid choice (1-2)\n");
			}

		} catch (NumberFormatException e) {
			log.info("Entry is not appropriate. Please Enter Valid Choice\n");
			continue;
		}
	} while (choice != 2);
	
}

public static void customerLogin() {
	int choice = 0;
	int custChance = 0;
	do {
		log.info("\n**** Welcome to Login Portal****");
		log.info("Enter your username");
		String username = scanner.nextLine();
		log.info("Enter your password");
		String password = scanner.nextLine();

		try {
			boolean valid = customerService.checkValidCredentials(username, password);
			if (valid) {
				
				log.info(" Hurray Login Successfully");
				log.info("Welcome " + username + ", What you wanna do today?");
				
				do {
					log.info("1)View Products");
					log.info("2)Search Products");
					log.info("3)View Orders");
					log.info("4)View Cart");
					log.info("5)Mark order as received!");
					log.info("6)LogOut");

					log.info("Enter your choice");
					try {
						choice = Integer.parseInt(scanner.nextLine());
					switch (choice) {
					case 1:
						viewAllProducts(); 
						break;
					case 2:
						searchProducts();													
						break;
					case 3:
						List<Order> orderList = orderService.getOrderList(customer.getCustomerId());
						log.info("There are "+ orderList.size() +" Orders and details are below --> \n");
						for (Order order : orderList) {
							log.info(order);
						}
						break;
					case 4:
						viewCart();
						break;
					case 5:
						markOrder(2, "Received");
						break;
					case 6:
						log.info("Logout Successfully");
						custChance = 5;
					default:
						log.warn("Please enter valid choice (1-5)\n");
					}
					}catch(BusinessException e) {
						 log.info(e.getMessage());
						 continue;
					 }
				} while (choice != 6);
			}
		} catch (BusinessException e) {
			log.info(e.getMessage());
			custChance++;
			if (custChance > 0)
				log.info("\nRemain chance to try login again is " + (5 - custChance) + "\n");

		}
	} while (custChance < 5);
	
}



public static void customersPortal() {
	
	log.info("\n**** Welcome to Customer Portal****");
	int choice = 0;
	do {
		log.info("1)Login");
		log.info("2)Create Accoount");
		log.info("3)Back to Main Menu");
		log.info("Enter your choice between 1-3");
		try {
			choice = Integer.parseInt(scanner.nextLine());
	
		switch (choice) {
		case 1:
			customerLogin();

			break;
		case 2:
			customerSignup();
			break;
		case 3:
			log.info("***Going to main menu***");
			break;
		default:
			log.warn("Please enter valid choice (1-3)\n");
		}
		} catch (NumberFormatException e) {
			log.info("Entry is not appropriate. Please Enter Valid Choice\n");
			continue;
		}
	} while (choice != 3);
}

public static void viewOrderForCustomer() {

	List<Order> orderList;
	try {
		orderList = orderService.getOrderList(customer.getCustomerId());
		log.info("There are " + orderList.size() + " Orders and details are below --> \n");
		for (Order order : orderList)
			log.info(order);
	} catch (BusinessException e) {
		log.info(e.getMessage());
	}

}

public static void customerOrderMark() {

	List<Order> orderList;
	try {
		orderList = orderService.markGetOrderList(customer.getCustomerId());
		log.info("There are " + orderList.size() + " Orders and details are below --> \n");
		for (Order order : orderList)
			log.info(order);
	} catch (BusinessException e) {
		log.info(e.getMessage());
	}

}


public static void employeeOrderMark() {

	List<Order> orderList;  
	try {
		orderList = orderService.getOrderList();
		log.info("There are " + orderList.size() + " Orders and details are below --> \n");
		for (Order order : orderList)
			log.info(order);
	} catch (BusinessException e) {
		log.info(e.getMessage());
	}

}


public static void markOrder(int id, String status) {
	int choice = 0;
	do {
		if (id == 1) {
			employeeOrderMark();
		}
		if (id == 2) {
			customerOrderMark();
		}
		log.info("********************************");
		log.info("1)Mark Status");
		log.info("2)Go to previous menu");
		try {
			choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
			case 1:
				log.info("Enter Order id to mark status");
				int orderId = Integer.parseInt(scanner.nextLine());

				if (orderService.updateOrderStatus(orderId, status) == 1)
					log.info("Mark Status is Successfully Done!!");
				break;
			case 2:
				log.info(" ****** going to previous menu *****");
				break;
			default:
				log.warn("Please enter valid choice (1-2)\n");
			}
		} catch (NumberFormatException e) {
			log.info("Entry is not appropriate. Please Enter Valid Choice\n");
		} catch (BusinessException e) {
			log.info(e.getMessage());
		}
	} while (choice != 2);
}

public static void main(String[] args) {		
	
	log.info("Welcome To Amozon Online Shopping App");
	log.info("********************************");
	int ch = 0;
	do {
		log.info("1) Enter as Employee");
		log.info("2) Enter as Customer ");
		log.info("3) Exit ");

		log.info("Enter your choice between 1-3");
		try {
			ch = Integer.parseInt(scanner.nextLine());
		
		switch (ch) {
		case 1:
			employeesPortal();
			break;
		case 2:
			customersPortal();
			break;
		case 3:
			log.info("Thank you for Visiting Amazon App ");
			break;
		default:
			log.warn("Please enter valid choice (1-3)\n");
		}
	} catch (NumberFormatException e) {
		log.info("Entry is not appropriate. Please Enter Valid Choice\n");
	}
	} while (ch != 3);
}
}