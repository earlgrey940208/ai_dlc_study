package com.tableorder.service;

import com.tableorder.dto.request.LoginRequest;
import com.tableorder.dto.request.TableLoginRequest;
import com.tableorder.dto.response.TokenResponse;
import com.tableorder.entity.Admin;
import com.tableorder.entity.TableInfo;
import com.tableorder.repository.AdminRepository;
import com.tableorder.repository.TableInfoRepository;
import com.tableorder.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminRepository adminRepository;
    private final TableInfoRepository tableInfoRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse login(LoginRequest request) {
        Admin admin = adminRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("인증 실패"));
        if (!passwordEncoder.matches(request.getPassword(), admin.getPasswordHash())) {
            throw new IllegalArgumentException("인증 실패");
        }
        String token = jwtTokenProvider.generateAdminToken(admin.getId());
        return TokenResponse.builder().token(token).role("ADMIN").build();
    }

    @Transactional
    public TokenResponse tableLogin(TableLoginRequest request) {
        TableInfo table = tableInfoRepository.findByTableNumber(request.getTableNumber())
                .orElseThrow(() -> new IllegalArgumentException("인증 실패"));
        if (!passwordEncoder.matches(request.getPassword(), table.getPasswordHash())) {
            throw new IllegalArgumentException("인증 실패");
        }
        if (!table.getActive()) {
            table.setActive(true);
            tableInfoRepository.save(table);
        }
        String token = jwtTokenProvider.generateTableToken(table.getId());
        return TokenResponse.builder().token(token).role("TABLE").tableId(table.getId()).build();
    }
}
