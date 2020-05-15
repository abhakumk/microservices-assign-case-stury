package com.ms.cs.login;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.cs.login.model.LoginResponse;
import com.ms.cs.login.model.UserDto;

/**
 * @author w2cluster03
 *
 */
@RestController
@RequestMapping("/cs")
public class LoginConrtoller {
		
	private static Logger LOGGER = LoggerFactory.getLogger(LoginConrtoller.class);
	
		@Autowired
		LoginService loginService;
		
		/**
		 * @param order
		 * @return
		 */
		@PostMapping(path = "/authenticate")
		public ResponseEntity<LoginResponse> authenticate(@RequestBody UserDto userDto) {
			LOGGER.info("authenticate().."+userDto.toString());
			LoginResponse loginResponse =  loginService.authenticate(userDto);
			return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
		}
		
		/**
		 * @param userDto
		 * @return
		 */
		@PatchMapping(path = "/user")
		public ResponseEntity<LoginResponse> reAuthenticateUser(@RequestBody UserDto userDto) {
			LOGGER.info("reAuthenticateUser().."+userDto.toString());
			try {
				LoginResponse loginResponse = loginService.updateUserDetail(userDto);
				return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<LoginResponse>(new LoginResponse(), HttpStatus.EXPECTATION_FAILED);
			}
		}
		
		/**
		 * @param userId
		 * @return
		 */
		@GetMapping(path = "/user/{userId}")
		public ResponseEntity<LoginResponse> getUser(@PathVariable String userId) {
			LOGGER.info("In getUser()..");
			try {
				LoginResponse loginResponse =  loginService.getUser(userId);
				if(loginResponse != null) {
					return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
				} 
					
			} catch (Exception e) {
				//TO-DO:log it 
			}
			return new ResponseEntity<LoginResponse>(HttpStatus.NOT_FOUND);
		}
		
		/**
		 * @param userId
		 * @return
		 */
		@DeleteMapping(path = "/user/{userId}")
		public ResponseEntity<String> deleteUser(@PathVariable String userId) {
			LOGGER.info("In deleteUser()..");
			try {
				String status =  loginService.deleteUser(userId);
				return new ResponseEntity<String>(status, HttpStatus.OK);
			} catch (Exception e) {
				//TO-DO:log it 
			}
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		
		/**
		 * @return
		 */
		@GetMapping(path = "/user")
		public Set<LoginResponse> getAllOrder() {
			return loginService.getUsers();
		}
		
}

		
