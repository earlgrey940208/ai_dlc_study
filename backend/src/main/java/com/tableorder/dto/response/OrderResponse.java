package com.tableorder.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderResponse {
    private Long id;
    private Long tableId;
    private Integer tableNumber;
    private String status;
    private Integer totalAmount;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> items;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class OrderItemResponse {
        private Long id;
        private Long menuId;
        private String menuName;
        private Integer price;
        private Integer quantity;
    }
}
