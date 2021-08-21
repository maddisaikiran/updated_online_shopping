package com.app.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.businessException.BusinessException;

import com.app.dao.OrderDAO;
import com.app.dao.util.MyDbConnection;
import com.app.model.Order;

public class OrderDAOImpl implements OrderDAO{

	@Override
	public int createOrder(int customerId, int productId, double price) throws BusinessException {
		int c =0;
		try (Connection connection = MyDbConnection.getConnection()) {
			
			String sql = "insert into online_shopping.order(customerId,productId,price,orderStatus) values(?,?,?,'Shipped')";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(1,customerId);
			preparedStatement.setInt(2,productId);
			preparedStatement.setDouble(3, price);
			c = preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			throw new BusinessException(e.getMessage()+" Internal Problem Occured. Contact sysAdmin!");
		}
		return c;
	}

	@Override
	public List<Order> getOrderList(int customerId) throws BusinessException {
     List<Order> orderList = new ArrayList<>();
		
		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "select orderId,customerId,orderStatus, o.productId ,p.productName,price from online_shopping.order o join online_shopping.product p on o.productId=p.productId where o.customerId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet = preparedStatement.executeQuery();			
			while(resultSet.next()) {
				Order order = new Order();
				order.setOrderId(resultSet.getInt("orderId"));
				order.setProductId(resultSet.getInt("productId"));
//				order.setCustomerId(resultSet.getInt("customerId"));
				order.setProductName(resultSet.getString("productName"));
				order.setPrice(resultSet.getDouble("price"));
				order.setOrderStatus(resultSet.getString("orderStatus"));
				orderList.add(order);
			}
			if(orderList.size()<1) {
				throw new BusinessException("You have no orders");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage()+" Internal Problem Occured. Contact sysAdmin!");
		}
		return orderList;
	
	}

	@Override
	public List<Order> getOrderList() throws BusinessException {
		List<Order> orderList = new ArrayList<>();

		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "select orderId,productId,productName,productPrice,orderStatus from order o join product p on o.customerId=p.productId join customer c on o.customerId= customerId where orderStatus=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "ordered");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				order.setOrderId(resultSet.getInt("orderId"));
				order.setProductId(resultSet.getInt("productId"));
				order.setProductName(resultSet.getString("productName"));
				order.setPrice(resultSet.getDouble("productPrice"));
				order.setOrderStatus(resultSet.getString("orderStatus"));
				orderList.add(order);
			}
			if (orderList.size() < 1) {
				throw new BusinessException("Customers have no orders Yet!!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage() + " Internal Problem Occured. Contact sysAdmin!");
		}
		return orderList;
	}

	@Override
	public List<Order> markGetOrderList(int customerId) throws BusinessException {
		List<Order> orderList = new ArrayList<>();

		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "select orderId,productId,productName,productPrice,orderStatus from order o join product p on o.productId= p.productId join customer c on o.customerId= c.customerId where c.customerId=? and orderStatus=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
			preparedStatement.setString(2, "shipped");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				order.setOrderId(resultSet.getInt("or_id"));
				order.setProductId(resultSet.getInt("pro_id"));
				order.setProductName(resultSet.getString("pro_name"));
				order.setPrice(resultSet.getDouble("pro_price"));
				order.setOrderStatus(resultSet.getString("or_status"));
				orderList.add(order);
			}
			if (orderList.size() < 1) {
				throw new BusinessException("Customers have no orders Yet!!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage() + " Internal Problem Occured. Contact sysAdmin!");
		}
		return orderList;
	}

	@Override
	public int updateOrderStatus(int orderId, String status) throws BusinessException {
		int c = 0;
		try (Connection connection = MyDbConnection.getConnection()) {

			String sql = "update orders set orderStatus = ? where orderId =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, orderId);
			
			c = preparedStatement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {

			throw new BusinessException(e.getMessage() + " Internal Problem Occured. Contact sysAdmin!");
		}
		return c;

	}

}
