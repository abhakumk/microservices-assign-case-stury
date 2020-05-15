package com.ms.cs.order.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class OrderDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3014417325254542820L;

	private String orderId;

	private String userToken;

	private String transactionToken;

	private String serviceToken;

	private Set<Item> items = new HashSet<>();
	/**
	 * 
	 */
	public OrderDto() {
		// Constructor
	}

	public OrderDto(String orderId, String userToken, String transactionToken,
			String serviceToken, Set<Item> items) {
		super();
		this.orderId = orderId;
		this.userToken = userToken;
		this.transactionToken = transactionToken;
		this.serviceToken = serviceToken;
		this.items = items;
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the userToken
	 */
	public String getUserToken() {
		return userToken;
	}

	/**
	 * @param userToken the userToken to set
	 */
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	/**
	 * @return the transactionToken
	 */
	public String getTransactionToken() {
		return transactionToken;
	}

	/**
	 * @param transactionToken the transactionToken to set
	 */
	public void setTransactionToken(String transactionToken) {
		this.transactionToken = transactionToken;
	}

	/**
	 * @return the serviceToken
	 */
	public String getServiceToken() {
		return serviceToken;
	}

	/**
	 * @param serviceToken the serviceToken to set
	 */
	public void setServiceToken(String serviceToken) {
		this.serviceToken = serviceToken;
	}

	/**
	 * @return the items
	 */
	public Set<Item> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(Set<Item> items) {
		this.items = items;
	}

}
