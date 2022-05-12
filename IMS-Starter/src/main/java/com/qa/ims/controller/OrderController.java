package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;

import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();
	private OrderDAO orderDAO;
	private Utils utils;
	
	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	// READING ORDERS //
	@Override
	public List<Order> readAll() {
		
		LOGGER.info("would you like to see all orders"
				+ "\n"
				+ "or see items in a order ?"
				+ "\n"
				+ "Please enter '1' to view all orders"
				+ "\n"
				+ "or enter '2' to view items in a order");

		String operation = utils.getString();
		switch (operation) {
		
		// ## Reading all the orders ##
		case "1":
			List<Order> orders = orderDAO.readAllA();
			for (Order order : orders) {
				LOGGER.info(order);
			}
			break;		
		case "2":
		// ## Reading all the items in a order
			LOGGER.info("Please enter the order ID you would like to see the contents of:");
			Long order_Id = utils.getLong();
			List<Order> order_items = orderDAO.read_Order(order_Id);
			for (Order order : order_items) {
				LOGGER.info(order);
			}
			break;	
		}
		return null;
	}
	

	// CREATEING A ORDER //
	@Override
	public Order create() {
		
		LOGGER.info("Please enter your user ID");
		Long customer_id = utils.getLong();
		Order order = orderDAO.create(new Order(customer_id));
		LOGGER.info("Order created");
		return order;
	}

	// UPDATING ITEMS IN A ORDER //
	
	@Override
	public Order update() {
		
		// ## Introduction ## 
		LOGGER.info("Please enter the ID of the order you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter the customer ID linked to the order");
		Long customer_Id = utils.getLong();
		LOGGER.info("would you like to 'ADD' or 'REMOVE' a item from this order");
		String operation = utils.getString();
		
		switch (operation) {
		case "ADD":
			LOGGER.info("Please enter the ID of the item you would like to add");
			Long item_Id = utils.getLong();
			LOGGER.info("\n"
					+ "Please enter how many of this item you would like");
			Long amount = utils.getLong();
			orderDAO.add(id, item_Id, amount, customer_Id);
			break;
		
		case "REMOVE":
			LOGGER.info("~~~### ORDER_ITEMS ###~~~");
			List<Order> order_items = orderDAO.read_Order(id);
			for (Order order : order_items) {
				LOGGER.info(order);	
			orderDAO.read_Order(id);
			}
			LOGGER.info("Please enter the 'order_items' ID you want to remove"
					+ "\n");
			Long id1 = utils.getLong();
			orderDAO.remove(id1);
		}
		LOGGER.info("Order Updated");
		return null;
	}

	// DELETING A ORDER AND ALL CONTENTS
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the Order you would like to delete"
				+ "\n"
				+ "WARNING THIS WILL REMOVE ALL THE ITEMS FROM THE ORDER AND THE ORDER");
		Long id = utils.getLong();
		orderDAO.deletePt1(id);
		orderDAO.deletePt2(id);
		return 0;
	}

}
