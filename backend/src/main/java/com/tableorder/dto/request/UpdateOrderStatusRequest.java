package com.tableorder.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UpdateOrderStatusRequest {
    @NotBlank private String status;
}
