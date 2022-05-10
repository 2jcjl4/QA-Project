package com.qa.ims.persistence.domain;

public class Order {

	private Long id;
	private Long customer_ID;

	public Order(Long id, Long customer_ID) {
		this.setId(id);
		this.setCustomer_ID(customer_ID);
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


	@Override
	public String toString() {
		return "id:" + id + " Customer ID:" + customer_ID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer_ID == null) ? 0 : customer_ID.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
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
		if (getCustomer_ID() == null) {
			if (other.getCustomer_ID() != null)
				return false;
		} else if (!getCustomer_ID().equals(other.getCustomer_ID()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
