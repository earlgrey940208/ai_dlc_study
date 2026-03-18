package com.tableorder.service;

import com.tableorder.dto.request.CreateOrderRequest;
import com.tableorder.dto.response.OrderResponse;
import com.tableorder.entity.*;
import com.tableorder.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MenuRepository menuRepository;
    private final TableInfoRepository tableInfoRepository;
    private final SseService sseService;

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        TableInfo table = tableInfoRepository.findById(request.getTableId())
                .orElseThrow(() -> new IllegalArgumentException("테이블을 찾을 수 없습니다"));
        if (!table.getActive()) {
            throw new IllegalStateException("비활성 테이블에서는 주문할 수 없습니다");
        }

        Order order = Order.builder()
                .tableId(table.getId())
                .status("PENDING")
                .totalAmount(0)
                .build();
        order = orderRepository.save(order);

        int totalAmount = 0;
        for (var itemReq : request.getItems()) {
            Menu menu = menuRepository.findById(itemReq.getMenuId())
                    .orElseThrow(() -> new IllegalArgumentException("메뉴를 찾을 수 없습니다: " + itemReq.getMenuId()));
            OrderItem item = OrderItem.builder()
                    .orderId(order.getId())
                    .menuId(menu.getId())
                    .menuName(menu.getName())
                    .price(menu.getPrice())
                    .quantity(itemReq.getQuantity())
                    .build();
            orderItemRepository.save(item);
            totalAmount += menu.getPrice() * itemReq.getQuantity();
        }
        order.setTotalAmount(totalAmount);
        order = orderRepository.save(order);

        sseService.broadcast("NEW_ORDER", Map.of(
                "orderId", order.getId(),
                "tableNumber", table.getTableNumber(),
                "totalAmount", totalAmount));

        return toResponse(order, table.getTableNumber());
    }

    public List<OrderResponse> getOrdersByTable(Long tableId) {
        TableInfo table = tableInfoRepository.findById(tableId)
                .orElseThrow(() -> new IllegalArgumentException("테이블을 찾을 수 없습니다"));
        return orderRepository.findByTableIdOrderByCreatedAtAsc(tableId).stream()
                .map(o -> toResponse(o, table.getTableNumber()))
                .toList();
    }

    @Transactional
    public OrderResponse updateOrderStatus(Long orderId, String newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다"));
        validateStatusTransition(order.getStatus(), newStatus);
        order.setStatus(newStatus);
        order = orderRepository.save(order);

        sseService.broadcast("ORDER_STATUS_CHANGED", Map.of(
                "orderId", order.getId(), "newStatus", newStatus));

        TableInfo table = tableInfoRepository.findById(order.getTableId()).orElse(null);
        return toResponse(order, table != null ? table.getTableNumber() : null);
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다"));
        TableInfo table = tableInfoRepository.findById(order.getTableId()).orElse(null);
        orderItemRepository.deleteByOrderId(orderId);
        orderRepository.delete(order);

        sseService.broadcast("ORDER_DELETED", Map.of(
                "orderId", orderId,
                "tableNumber", table != null ? table.getTableNumber() : 0));
    }

    private void validateStatusTransition(String current, String next) {
        boolean valid = ("PENDING".equals(current) && "PREPARING".equals(next))
                || ("PREPARING".equals(current) && "COMPLETED".equals(next));
        if (!valid) {
            throw new IllegalStateException("잘못된 상태 전이: " + current + " → " + next);
        }
    }

    private OrderResponse toResponse(Order order, Integer tableNumber) {
        List<OrderItem> items = orderItemRepository.findByOrderId(order.getId());
        return OrderResponse.builder()
                .id(order.getId())
                .tableId(order.getTableId())
                .tableNumber(tableNumber)
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .createdAt(order.getCreatedAt())
                .items(items.stream().map(i -> OrderResponse.OrderItemResponse.builder()
                        .id(i.getId())
                        .menuId(i.getMenuId())
                        .menuName(i.getMenuName())
                        .price(i.getPrice())
                        .quantity(i.getQuantity())
                        .build()).toList())
                .build();
    }
}
