package com.ms.cs.login.model;

import java.io.Serializable;

/**
 * @author w2mservices220
 *
 */
public class ServiceResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1579982691597622064L;
	private String access_token;
	private String refresh_token;
	private String token_type;
	private Integer expires_in;
	private Integer expiration;
	private String scope;
	/**
	 * @return the access_token
	 */
	public String getAccess_token() {
		return access_token;
	}
	/**
	 * @param access_token the access_token to set
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	/**
	 * @return the token_type
	 */
	public String getToken_type() {
		return token_type;
	}
	/**
	 * @param token_type the token_type to set
	 */
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	/**
	 * @return the expires_in
	 */
	public Integer getExpires_in() {
		return expires_in;
	}
	/**
	 * @param expires_in the expires_in to set
	 */
	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
	/**
	 * @return the expiration
	 */
	public Integer getExpiration() {
		return expiration;
	}
	/**
	 * @param expiration the expiration to set
	 */
	public void setExpiration(Integer expiration) {
		this.expiration = expiration;
	}
	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}
	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
	/**
	 *
	 */
	@Override
	public String toString() {
		return "ServiceResponse [access_token=" + access_token + ", token_type=" + token_type + ", expires_in="
				+ expires_in + ", expiration=" + expiration + ", scope=" + scope + "]";
	}
	/**
	 * @return the refresh_token
	 */
	public String getRefresh_token() {
		return refresh_token;
	}
	/**
	 * @param refresh_token the refresh_token to set
	 */
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

}
