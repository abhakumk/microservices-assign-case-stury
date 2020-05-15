package com.ms.cs.login.model;

import java.io.Serializable;

/**
 * @author w2mservices220
 *
 */
public class TokenDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4806972414793599812L;

	private String userToken;

	private String transactionToken;

	private String serviceToken;

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
}
