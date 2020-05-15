package com.ms.cs.order.model;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepo extends JpaRepository<Item, Long> {
	@Query("select t from Item t where t.order.orderId = ?1")
	Set<Item> findByOrder(String orderId);
}
