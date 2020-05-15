package com.ms.cs.order.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author w2cluster03
 *
 */
public interface OrderRepo  extends JpaRepository<Order, String> {

}
