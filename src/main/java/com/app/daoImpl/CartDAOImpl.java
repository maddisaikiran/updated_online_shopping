package com.app.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.businessException.BusinessException;
import com.app.dao.CartDAO;

import com.app.dao.util.MyDbConnection;
import com.app.model.Cart;


public class CartDAOImpl implements CartDAO{

	@Override
	public int addProductCart(int productId, int customerId) throws BusinessException {
		int c;
		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "insert into cart(customerId,productId) values (?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1,customerId);
			preparedStatement.setInt(2,productId);
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage()+ " Internal Problem Occured. Contacts sysAdmin!");
		}
		return c;
	}

	@Override
	public List<Cart> getProductCart(int customerId) throws BusinessException {
		List<Cart> cartList = new ArrayList<>();
		try (Connection connection = MyDbConnection.getConnection()) {
		
			String sql = "select customerId,c.productId,p.productName,p.productPrice from cart c join product p on c.productId=p.productId where c.customerId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet = preparedStatement.executeQuery();			
			while(resultSet.next()) {
				Cart cart = new Cart();
				cart.setCustomerId(customerId);
				cart.setProductId(resultSet.getInt("productId"));
				cart.setProductName(resultSet.getString("productName"));
				cart.setPrice(resultSet.getInt("productPrice"));
				cartList.add(cart);
			}
			if(cartList.size()<1) {
				throw new BusinessException("Sorry Cart is Empty!!");
			}
		}catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage()+ " Internal Problem Occured. Contact sysAdmin!");
		}
		return cartList;	
	}

	@Override
	public int deleteProductCart(int customerId) throws BusinessException {
		int c = 0;
		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "delete from cart where customerId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,customerId);
			c = preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage()+ " Internal Problem Occured. Contact sysAdmin!");
		}
		return c;
	}

}
