package com.ms.cs.login.model;

import java.io.Serializable;

/**
 * @author w2cluster0220
 *
 */
public class LoginResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4508820299024047829L;

	private String userId;

	private TokenDetail tokenDetail;

	private String status;

	public LoginResponse() {
		// Constructor
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the tokenDetail
	 */
	public TokenDetail getTokenDetail() {
		return tokenDetail;
	}

	/**
	 * @param tokenDetail the tokenDetail to set
	 */
	public void setTokenDetail(TokenDetail tokenDetail) {
		this.tokenDetail = tokenDetail;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
