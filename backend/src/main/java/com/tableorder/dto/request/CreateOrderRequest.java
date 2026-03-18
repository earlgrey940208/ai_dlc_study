package com.tableorder.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CreateOrderRequest {
    @NotNull private Long tableId;
    @NotEmpty @Valid private List<OrderItemRequest> items;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class OrderItemRequest {
        @NotNull private Long menuId;
        @NotNull @jakarta.validation.constraints.Min(1) private Integer quantity;
    }
}
