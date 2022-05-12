package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {

	private final OrderDAO DAO = new OrderDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}


	@Test
	public void testCreate() {
		final Order created = new Order(2L,1L);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAllA() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L,1L));
		assertEquals(expected, DAO.readAllA());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Order(1L,1L), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final Long ID = 1L;
		final Long customer_Id = 1L;
		assertEquals(new Order(ID, customer_Id), DAO.read(ID));
	}

	@Test
	public void testUpdateAdd() {
		final Long id = 1L;

		final Order add = new Order(2L, id, id, id, id);
		assertEquals(add, DAO.add(id, id, id, id));

	}

	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(1));
	}
	
	@Test
	public void testRead_Order() {
		List<Order> expected = new ArrayList<>();
		final Long order_Id = 1L;
		final Long ID = 1L;
		final Long amount = 123L;
		Order order = new Order(1L,ID, ID, ID , amount);
		expected.add(order);
		assertEquals(expected, DAO.read_Order(order_Id));
	}
	
	@Test
	public void testRemove() {
		assertEquals(1, DAO.remove(1L));
	}
	
	@Test
	public void deletePt1() {
		assertEquals(1, DAO.deletePt1(1));
	}
	
	@Test
	public void deletePt2() {
		assertEquals(0, DAO.deletePt2(1L));
	}
	
}
