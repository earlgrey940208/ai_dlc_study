package com.tableorder.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TableLoginRequest {
    @NotNull @Min(1) private Integer tableNumber;
    @NotBlank private String password;
}
