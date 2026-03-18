package com.tableorder.controller;

import com.tableorder.dto.request.LoginRequest;
import com.tableorder.dto.request.TableLoginRequest;
import com.tableorder.dto.response.TokenResponse;
import com.tableorder.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/table-login")
    public ResponseEntity<TokenResponse> tableLogin(@Valid @RequestBody TableLoginRequest request) {
        return ResponseEntity.ok(authService.tableLogin(request));
    }
}
