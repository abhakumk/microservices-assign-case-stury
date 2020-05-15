package com.ms.cs.login;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ms.cs.exception.ResourceNotFoundException;
import com.ms.cs.login.model.LoginResponse;
import com.ms.cs.login.model.ServiceResponse;
import com.ms.cs.login.model.UserDto;
import com.ms.cs.login.model.UserStore;
import com.ms.cs.login.util.CommonUtil;

/**
 * @author w2cluster03
 *
 */
@Component
public class LoginService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
	
	public static final String GRANT_TYPE = "grant_type";
	public static final String API_KEY = "apikey";
	public static final String IBMCLOUD_GRANT_TYPE_VALUE = "urn:ibm:params:oauth:grant-type:apikey";
	public static final String IBMCLOUD_API_KEY_VALUE = "01WOKe8MNYOPSpd_F47ntPAMyY1aSApRVK6slb_hxIHR";
	public static final String IBMCLOUD_IAM_SERVER_URI = "https://iam.cloud.ibm.com/oidc/token";
	
		/**
		 * @param userDto
		 * @return
		 */
		public LoginResponse authenticate(UserDto userDto) {
			LoginResponse loginResponse = new LoginResponse();
			loginResponse.setUserId(userDto.getUserId());
			if(UserStore.exists(userDto.getUserId())) {
				loginResponse.setTokenDetail(UserStore.getToken(userDto.getUserId()));
	        } else {
	        	String accessToken = getAccessToken(userDto);
	        	if(CommonUtil.isNullOrEmpty(accessToken)) {
	        		
	        		loginResponse.setStatus("Failed to get access token!");
	        	} else {
		        	loginResponse.setTokenDetail(TokenGenerator.createTokens(accessToken));
		        	UserStore.save(userDto.getUserId(), loginResponse.getTokenDetail());
		        	loginResponse.setStatus("Success");
	        	}
	        }
			return loginResponse;
		}

		/**
		 * @param userDto
		 * @return
		 */
		private String getAccessToken(UserDto userDto) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> form =
					 new LinkedMultiValueMap<String, String>();
			form.add(GRANT_TYPE, IBMCLOUD_GRANT_TYPE_VALUE ); 
			form.add(API_KEY, IBMCLOUD_API_KEY_VALUE);
	        
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);
//	        RestTemplate restTemplate = (new RestTemplateBuilder()).build();
			ServiceResponse serviceResponse = null;
			try {
		        ResponseEntity<?> responseEntity = new RestTemplate().postForEntity(IBMCLOUD_IAM_SERVER_URI, request, String.class);
		        String response = "{\"ServiceResponse\":"+responseEntity.getBody()+"}";
		        serviceResponse = (ServiceResponse) CommonUtil.getJSONObject(response, ServiceResponse.class);
		        return serviceResponse.getAccess_token();
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
			return null;
		}

		/**
		 * @return
		 */
		public Set<LoginResponse> getUsers() {
			Set<LoginResponse> loginResponseSet = new HashSet<>();
			if(!UserStore.userMap.isEmpty()) {
				UserStore.userMap.forEach((k, v) -> {
					LoginResponse loginResponse = new LoginResponse();
					loginResponse.setUserId(k);
					loginResponse.setTokenDetail(v);
					loginResponseSet.add(loginResponse);
				});
			}
			return loginResponseSet;
		}
		
		
		/**
		 * @param countryCode
		 * @param convFactor
		 * @return
		 */
		public LoginResponse updateUserDetail(UserDto userDto) {
			if(!UserStore.exists(userDto.getUserId())) {
	            throw new ResourceNotFoundException("UserId " + userDto.getUserId() + " not found");
	        }
			LoginResponse loginResponse = null;
			try {
				return authenticate(userDto);
			} catch (Exception e) {
				loginResponse = new LoginResponse();
				loginResponse.setStatus("Failed! - "+ e.getMessage());
			}
			return loginResponse;
		}
		
		/**
		 * @param orderId
		 * @return
		 */
		public LoginResponse getUser(String userId) {
				LoginResponse loginResponse =  new LoginResponse();
				loginResponse.setUserId(userId);
				loginResponse.setTokenDetail(UserStore.getToken(userId));
				loginResponse.setStatus("Success");
			return null;
		}

		/**
		 * @param userId
		 * @return
		 */
		public String deleteUser(String userId) {
			if(!UserStore.exists(userId)) {
	            throw new ResourceNotFoundException("userId " + userId + " not found");
	        }
			UserStore.userMap.remove(userId);
			return "Deleted successfully.";
		}

	}
