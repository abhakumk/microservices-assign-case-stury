package com.ms.cs.order;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.cs.order.model.OrderDto;

/**
 * @author w2cluster03
 *
 */
@RestController
@RequestMapping("/cs")
public class ReceiveOrderConrtoller {
		
	private static Logger LOGGER = LoggerFactory.getLogger(ReceiveOrderConrtoller.class);
	
		@Autowired
		ReceiveOrderService receiveOrderService;
		
		/**
		 * @param order
		 * @return
		 */
		@PostMapping(path = "/order")
		public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto) {
			LOGGER.info("In createOrder().."+orderDto.toString());
			try {
				String status =  receiveOrderService.createOrder(orderDto);
				return new ResponseEntity<String>(status, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("Failed! - "+e.getMessage(), HttpStatus.EXPECTATION_FAILED);
			}
		}
		
		/**
		 * @param orderDto
		 * @return
		 */
		@PatchMapping(path = "/order")
		public ResponseEntity<String> updateOrder(@RequestBody OrderDto orderDto) {
			LOGGER.info("In updateOrder().."+orderDto.toString());
			try {
				String status =  receiveOrderService.updateOrderDetail(orderDto);
				return new ResponseEntity<String>(status, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("Failed! - "+e.getMessage(), HttpStatus.EXPECTATION_FAILED);
			}
		}
		
		/**
		 * @param orderId
		 * @return
		 */
		@GetMapping(path = "/order/{orderId}")
		public ResponseEntity<OrderDto> getOrder(@PathVariable String orderId) {
			LOGGER.info("In getOrder()..");
			try {
				OrderDto order =  receiveOrderService.getOrder(orderId);
				if(order != null) {
					return new ResponseEntity<OrderDto>(order, HttpStatus.OK);
				} 
					
			} catch (Exception e) {
				//TO-DO:log it 
			}
			return new ResponseEntity<OrderDto>(HttpStatus.NOT_FOUND);
		}
		
		/**
		 * @return
		 */
		@GetMapping(path = "/order")
		public Set<OrderDto> getAllOrder() {
			return receiveOrderService.getOrders();
		}
		
}

		
