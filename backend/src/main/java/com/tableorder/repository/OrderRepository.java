package com.tableorder.repository;

import com.tableorder.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByTableIdOrderByCreatedAtAsc(Long tableId);
    void deleteByTableId(Long tableId);
}
