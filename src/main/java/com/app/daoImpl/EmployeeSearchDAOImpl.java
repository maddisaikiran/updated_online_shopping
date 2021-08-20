package com.app.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.Main;
import com.app.businessException.BusinessException;
import com.app.dao.EmployeeSearchDAO;
import com.app.dao.util.MyDbConnection;
import com.app.model.Product;



public class EmployeeSearchDAOImpl implements EmployeeSearchDAO {
	private static Logger log = Logger.getLogger(Main.class);

	@Override
	public Product getProductId(int productid) throws BusinessException {
		Product product=null;
		try(Connection connection=MyDbConnection.getConnection()){
			String sql="select productId,productName,productPrice,productRating,productCategoryId from product where productId=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, productid);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				product=new Product();
				product.setProductId(productid);
				product.setProductName(resultSet.getString("productname"));
				product.setProductPrice(resultSet.getInt("productPrice"));
				product.setProductRating(resultSet.getDouble("productRating"));
				product.setProductCategoryId(resultSet.getInt("productCategoryId"));
			}else {
				throw new BusinessException("Entered player id "+productid+" doesnt exist");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
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
