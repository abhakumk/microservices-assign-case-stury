package com.ms.cs.login.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author w2cluster03
 *
 */
public class UserStore {
	
	public static final Map<String, TokenDetail> userMap = new HashMap<>(); 
	
	/**
	 * @param userId
	 * @param tokenDetail
	 */
	public static void save(String userId, TokenDetail tokenDetail) {
		userMap.put(userId, tokenDetail);
	}
	
	/**
	 * @param userId
	 * @return
	 */
	public static TokenDetail getToken(String userId) {
		return userMap.get(userId);
	}
	
	/**
	 * @param userId
	 * @return
	 */
	public static boolean exists(String userId) {
		return userMap.containsKey(userId);
	}

}
