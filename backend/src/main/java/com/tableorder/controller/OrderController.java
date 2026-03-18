package com.tableorder.controller;

import com.tableorder.dto.request.CreateOrderRequest;
import com.tableorder.dto.request.UpdateOrderStatusRequest;
import com.tableorder.dto.response.OrderResponse;
import com.tableorder.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping("/list")
    public ResponseEntity<List<OrderResponse>> getOrdersByTable(@RequestParam Long tableId) {
        return ResponseEntity.ok(orderService.getOrdersByTable(tableId));
    }

    @PutMapping("/update-status/{orderId}")
    public ResponseEntity<OrderResponse> updateOrderStatus(
            @PathVariable Long orderId,
            @Valid @RequestBody UpdateOrderStatusRequest request) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, request.getStatus()));
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
