package com.ms.cs.login;

import java.util.UUID;

import com.ms.cs.login.model.TokenDetail;
import com.ms.cs.login.util.CommonUtil;

/**
 * @author w2mservices220
 *
 */
public class TokenGenerator {
	/**
	 * @param accessToken
	 * @return
	 */
	public static TokenDetail createTokens(String accessToken) {
		TokenDetail tokenDetail = new TokenDetail();
		String tokenString = "";
		if(!CommonUtil.isNullOrEmpty(accessToken)) {
			tokenString = accessToken.substring(7, 40);
		}
		tokenDetail.setUserToken(UUID.randomUUID()+tokenString);
		tokenDetail.setTransactionToken(UUID.randomUUID()+tokenString);
		tokenDetail.setServiceToken(UUID.randomUUID()+tokenString);
		return tokenDetail;
	}
}
