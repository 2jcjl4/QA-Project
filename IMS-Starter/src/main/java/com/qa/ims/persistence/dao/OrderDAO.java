package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;


public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();
	
	
	// READING ORDERS //
	@Override
	public List<Order> readAll() {
		return null;
	}

	@Override
	public Order read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	// ## Display orders ## 
	
	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long customer_ID = resultSet.getLong("customer_ID");
		return new Order(id, customer_ID);
	}

	// ## Displays items in a order ##
	
	public Order modelFromResultSet1(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long customer_ID = resultSet.getLong("customer_ID");
		Long order_ID = resultSet.getLong("orders_id");
		Long product_ID = resultSet.getLong("products_id");
		Long amount = resultSet.getLong("amount");
		return new Order(id, customer_ID, order_ID, product_ID, amount);
	}

	// ## Reads all the orders out ##
	public List<Order> readAllA() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			List<Order> item = new ArrayList<>();
			while (resultSet.next()) {
				item.add(modelFromResultSet(resultSet));
			}
			return item;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	// ## Reads what has just been done in other methods 
	// 	  ( such as create method) ##
	
	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public Order readLatest1() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM order_items ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet1(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	// ## Read items from a order ## 

	public List<Order> read_Order(Long order_Id) {
		List<Order> item = new ArrayList<>();
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM order_items WHERE orders_id = ?");) {
			statement.setLong(1, order_Id);
			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {
					item.add(modelFromResultSet1(resultSet));		
			}
			resultSet.next();	
			}
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return item;
	}
	
	// CREATES A ORDER // 
	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders(customer_id) VALUES (?)");) {
			statement.setLong(1, order.getCustomer_ID());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	
	
	

	// UPDATING ORDERS
	
	@Override
	public Order update(Order order) {
		return null;
	}
	
	//ADDING A ITEM TO A ORDER
	public Order add(Long id, Long item_Id, Long amount, Long customer_Id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO order_items(orders_id, products_id, amount, customer_id) VALUES (?, ?, ?, ?)");) {
			statement.setLong(1, id);
			statement.setLong(2, item_Id);
			statement.setLong(3, amount);
			statement.setLong(4, customer_Id);
			statement.executeUpdate();
			return readLatest1();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		LOGGER.info("Item has been added to your order");
		return null;
	}
	// REMOVING A ITEM FROM A ORDER
	public int remove(Long id1) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM order_items WHERE id = ?");) {
			statement.setLong(1, id1);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	// DELETE
	
	@Override
	public int delete(long id) {
		return 0;
	}
	
	// Deletes all the items in the order
	public int deletePt1(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM order_items WHERE orders_id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	// DELETES THE ORDER
	public int deletePt2(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
