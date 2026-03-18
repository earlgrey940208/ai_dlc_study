package com.tableorder.repository;

import com.tableorder.entity.TableInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TableInfoRepository extends JpaRepository<TableInfo, Long> {
    Optional<TableInfo> findByTableNumber(Integer tableNumber);
    boolean existsByTableNumber(Integer tableNumber);
}
