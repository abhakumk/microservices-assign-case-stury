package com.ms.cs.login.util;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtil {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);
	public static final String NULL = "null";
	
	private CommonUtil() {
		
	}

	/**
	 * This method returns true if a string value is null or length is 0 or has only
	 * blank space(s)
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNullOrEmpty(String value) {
		return value == null || value.trim().length() == 0 || NULL.equalsIgnoreCase(value);
	}
	
	/**
	 * @param list
	 * @return
	 */
	public static boolean isEmptyList(Collection<?> list) {
		if (list == null || list.isEmpty())
			return true;

		for (Iterator<?> it = list.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj != null) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @param jsonString
	 * @param requestedClass
	 * @return Object
	 */
	public static Object getJSONObject(String jsonString,
			Class<?> requestedClass) {
		if(CommonUtil.isNullOrEmpty(jsonString)){
			LOGGER.error("getJSONObject: Invalid Response!, jsonString= "+jsonString);
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		//Added to remove null and empty fields
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.setSerializationInclusion(Include.NON_EMPTY);
		try {
			return mapper.readValue(jsonString, requestedClass);
		} catch (IOException e) {
			LOGGER.error("Unmarshalling Exception during readValue() within getJSONObject(): "+e.getMessage());
		}
		return null;
	}
	
}
