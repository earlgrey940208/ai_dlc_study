package com.tableorder.service;

import com.tableorder.dto.request.SetupTableRequest;
import com.tableorder.entity.*;
import com.tableorder.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableInfoRepository tableInfoRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    @Transactional
    public TableInfo setupTable(SetupTableRequest request) {
        if (tableInfoRepository.existsByTableNumber(request.getTableNumber())) {
            throw new IllegalArgumentException("이미 존재하는 테이블 번호입니다");
        }
        TableInfo table = TableInfo.builder()
                .tableNumber(request.getTableNumber())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .active(true)
                .build();
        return tableInfoRepository.save(table);
    }

    public List<TableInfo> getTableList() {
        return tableInfoRepository.findAll();
    }

    @Transactional
    public void completeTable(Long tableId) {
        TableInfo table = tableInfoRepository.findById(tableId)
                .orElseThrow(() -> new IllegalArgumentException("테이블을 찾을 수 없습니다"));
        if (!table.getActive()) {
            throw new IllegalStateException("이미 비활성 상태인 테이블입니다");
        }

        List<Order> orders = orderRepository.findByTableIdOrderByCreatedAtAsc(tableId);
        if (!orders.isEmpty()) {
            int totalAmount = orders.stream().mapToInt(Order::getTotalAmount).sum();
            try {
                List<Long> orderIds = orders.stream().map(Order::getId).toList();
                var orderData = orders.stream().map(o -> {
                    List<OrderItem> items = orderItemRepository.findByOrderId(o.getId());
                    return new java.util.HashMap<String, Object>() {{
                        put("orderId", o.getId());
                        put("status", o.getStatus());
                        put("totalAmount", o.getTotalAmount());
                        put("createdAt", o.getCreatedAt().toString());
                        put("items", items);
                    }};
                }).toList();
                String json = objectMapper.writeValueAsString(orderData);

                OrderHistory history = OrderHistory.builder()
                        .tableId(tableId)
                        .orderData(json)
                        .totalAmount(totalAmount)
                        .build();
                orderHistoryRepository.save(history);

                orderItemRepository.deleteByOrderIdIn(orderIds);
                orderRepository.deleteByTableId(tableId);
            } catch (Exception e) {
                throw new RuntimeException("이용 완료 처리 중 오류", e);
            }
        }

        table.setActive(false);
        tableInfoRepository.save(table);
    }

    public List<OrderHistory> getTableHistory(Long tableId, LocalDate date) {
        if (date != null) {
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.plusDays(1).atStartOfDay();
            return orderHistoryRepository.findByTableIdAndCompletedAtBetweenOrderByCompletedAtDesc(
                    tableId, start, end);
        }
        return orderHistoryRepository.findByTableIdOrderByCompletedAtDesc(tableId);
    }
}
