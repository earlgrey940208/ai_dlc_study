package com.tableorder.dto.response;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TokenResponse {
    private String token;
    private String role;
    private Long tableId; // null for admin
}
