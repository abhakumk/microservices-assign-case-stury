package com.ms.cs.order;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.cs.exception.ResourceException;
import com.ms.cs.order.model.Item;
import com.ms.cs.order.model.ItemRepo;
import com.ms.cs.order.model.Order;
import com.ms.cs.order.model.OrderDto;
import com.ms.cs.order.model.OrderRepo;

/**
 * @author w2cluster03
 *
 */
@Component
public class ReceiveOrderService {
	
		@Autowired
		private OrderRepo orderRepo;
		
		@Autowired
		private ItemRepo itemRepo;
			
		/**
		 * @return
		 */
		public Set<OrderDto> getOrders() {
			Set<OrderDto> orderDtoSet = new HashSet<>();
			List<Order> orders = orderRepo.findAll();
			if(orders != null && !orders.isEmpty()) {
				orderDtoSet = orders.stream().map(order -> order.mapOrderDto()).collect(Collectors.toSet());
				orderDtoSet.forEach(orderDto -> {
					orderDto.setItems(itemRepo.findByOrder(orderDto.getOrderId()));
				});
			}
			return orderDtoSet;
		}
		
		/**
		 * @param countryCode
		 * @param convFactor
		 * @return
		 */
		public String createOrder(OrderDto orderDto) {
			if(orderRepo.existsById(orderDto.getOrderId())) {
	            throw new ResourceException("OrderId " + orderDto.getOrderId() + " already exist!");
	        }
			Order order =  orderRepo.save(new Order(orderDto.getOrderId(), orderDto.getUserToken(), orderDto.getTransactionToken(), orderDto.getServiceToken()));
			if(orderDto.getItems() != null && !orderDto.getItems().isEmpty()) {
				for(Item item : orderDto.getItems()) {
					Item itemTmp = new Item(item.getItemCode(), item.getDescription(), item.getQuantity(), order);
					itemRepo.save(itemTmp);
					itemRepo.flush();
				}
			}
			
			if(order != null) {
				return "Successfuly Created.";
			}
			return "Failed!";
		}
		
		/**
		 * @param countryCode
		 * @param convFactor
		 * @return
		 */
		public String updateOrderDetail(OrderDto orderDto) {
			if(!orderRepo.existsById(orderDto.getOrderId())) {
	            throw new ResourceException("OrderId " + orderDto.getOrderId() + " not found");
	        }
			try {
				Optional<Order> orderOpt =  orderRepo.findById(orderDto.getOrderId());
				if(orderOpt.isPresent()) {
					Order orderDetail = orderOpt.get();
					orderDetail.setUserToken(orderDto.getUserToken());
					orderDetail.setTransactionToken(orderDto.getTransactionToken());
					orderDetail.setServiceToken(orderDto.getServiceToken());
					orderDetail = orderRepo.saveAndFlush(orderDetail);
					updateItems(orderDetail, orderDto);
					if(orderDetail != null) {
						return "Successfuly Updated.";
					}
				} else {
					return "Failed! No Record Found.";
				}
			} catch (Exception e) {
				return "Failed! - "+ e.getMessage();
			}
			return "Failed!";
		}
		
		/**
		 * @param order
		 * @param orderDto
		 */
		private void updateItems(Order order, OrderDto orderDto) {
			Set<Item> items = itemRepo.findByOrder(orderDto.getOrderId());
			if(items != null && !items.isEmpty()) {
				items.forEach( item -> {
					itemRepo.delete(item);
				});
			}
			if(orderDto.getItems() != null && !orderDto.getItems().isEmpty()) {
				orderDto.getItems().forEach( item -> {
					Item itemTmp = new Item(item.getItemCode(), item.getDescription(), item.getQuantity(), order);
					itemRepo.save(itemTmp);
					itemRepo.flush();
				});
			}
			
		}

		/**
		 * @param orderId
		 * @return
		 */
		public OrderDto getOrder(String orderId) {
			OrderDto orderDto = null;
			Optional<Order> order =  orderRepo.findById(orderId);
			if(order.isPresent()) {
				orderDto = order.get().mapOrderDto();
				orderDto.setItems(itemRepo.findByOrder(orderDto.getOrderId()));
			} 
			return orderDto;
		}
		
	}
