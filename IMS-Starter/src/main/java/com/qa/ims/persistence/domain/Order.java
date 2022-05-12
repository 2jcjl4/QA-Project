package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Order {

	private Long id;
	private Long customer_ID;
	private Long order_ID;
	private Long product_ID;
	private Long amount;
	
	public Order(Long customer_id) {
		this.setCustomer_ID(customer_id);
	}

	public Order(Long id, Long customer_ID) {
		this.setId(id);
		this.setCustomer_ID(customer_ID);
	}

	public Order(Long id, Long customer_ID, Long order_ID, Long product_ID, Long amount) {
		this.id = id;
		this.customer_ID = customer_ID;
		this.order_ID = order_ID;
		this.product_ID = product_ID;
		this.amount = amount;
	}

	
	
	// GETTERS AND SETTERS

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomer_ID() {
		return customer_ID;
	}

	public void setCustomer_ID(Long customer_ID) {
		this.customer_ID = customer_ID;
	}

	public Long getOrder_ID() {
		return order_ID;
	}

	public void setOrder_ID(Long order_ID) {
		this.order_ID = order_ID;
	}

	public Long getProduct_ID() {
		return product_ID;
	}

	public void setProduct_ID(Long product_ID) {
		this.product_ID = product_ID;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	
	
	
	@Override
	public String toString() {
		if (order_ID == null) {
			return "Order [id=" + id + ", customer_ID=" + customer_ID + "]";
		}else {
		return "Order [id=" + id + ", customer_ID=" + customer_ID + ", order_ID=" + order_ID + ", product_ID="
				+ product_ID + ", amount=" + amount + "]";
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, customer_ID, id, order_ID, product_ID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(customer_ID, other.customer_ID)
				&& Objects.equals(id, other.id) && Objects.equals(order_ID, other.order_ID)
				&& Objects.equals(product_ID, other.product_ID);
	}


	
	

}
