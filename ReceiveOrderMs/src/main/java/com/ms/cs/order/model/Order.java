package com.ms.cs.order.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author w2cluster0220
 *
 */
@Entity(name = "ORDERDETAIL")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4548220283637350077L;
	@Id
	private String orderId;

	@Column(name = "USERTOKEN")
	private String userToken;

	@Column(name = "TRANSACTIONTOKEN")
	private String transactionToken;

	@Column(name = "SERVICETOKEN")
	private String serviceToken;

//	@OneToMany(targetEntity = Item.class, mappedBy = "id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private List<Item> items = new ArrayList<Item>();

//	@OneToMany(cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
//            mappedBy = "id")
//	private Set<Item> items = new HashSet<>();
	/**
	 * 
	 */
	public Order() {
		// Constructor
	}

	public Order(String orderId, String userToken, String transactionToken,
			String serviceToken) {
		super();
		this.orderId = orderId;
		this.userToken = userToken;
		this.transactionToken = transactionToken;
		this.serviceToken = serviceToken;
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
	 * @return
	 */
	public OrderDto mapOrderDto() {
		OrderDto orderDto = new OrderDto();
		orderDto.setOrderId(orderId);
		orderDto.setServiceToken(serviceToken);
		orderDto.setTransactionToken(transactionToken);
		orderDto.setUserToken(userToken);
		return orderDto;
	}
}
