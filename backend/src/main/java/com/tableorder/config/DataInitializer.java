package com.tableorder.config;

import com.tableorder.entity.Admin;
import com.tableorder.entity.TableInfo;
import com.tableorder.repository.AdminRepository;
import com.tableorder.repository.TableInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final TableInfoRepository tableInfoRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Admin 계정: admin / admin123
        if (adminRepository.count() == 0) {
            adminRepository.save(Admin.builder()
                    .username("admin")
                    .passwordHash(passwordEncoder.encode("admin123"))
                    .build());
        }

        // 테이블 1~5번: 비밀번호 1234
        if (tableInfoRepository.count() == 0) {
            for (int i = 1; i <= 5; i++) {
                tableInfoRepository.save(TableInfo.builder()
                        .tableNumber(i)
                        .passwordHash(passwordEncoder.encode("1234"))
                        .active(false)
                        .build());
            }
        }
    }
}
