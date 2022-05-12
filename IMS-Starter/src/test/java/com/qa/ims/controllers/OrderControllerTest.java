package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;

	@Test
	public void testCreate() {
		final Long id = (long) 1;
		final Order created = new Order(id);

		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}
	
	@Test
	public void testReadAllPt1() {
		Mockito.when(utils.getString()).thenReturn("1");	
		assertEquals(null, controller.readAll());	
	}
	
	@Test
	public void testReadAllPt2() {
		Mockito.when(utils.getString()).thenReturn("2");
		assertEquals(null, controller.readAll());
	}
	
	@Test
	public void testUpdateAdd() {
		Mockito.when(utils.getString()).thenReturn("ADD");
		assertEquals(null, controller.update());	
	}
	@Test
	public void testUpdateRemove() {
		Mockito.when(utils.getString()).thenReturn("REMOVE");
		assertEquals(null, controller.update());	
	}
	@Test
	public void testDelete() {
		Mockito.when(utils.getLong()).thenReturn(1L);
		assertEquals(0, controller.delete());
	}
}
