package com.tableorder.controller;

import com.tableorder.dto.request.SetupTableRequest;
import com.tableorder.entity.OrderHistory;
import com.tableorder.entity.TableInfo;
import com.tableorder.service.TableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/table")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @PostMapping("/setup")
    public ResponseEntity<TableInfo> setupTable(@Valid @RequestBody SetupTableRequest request) {
        return ResponseEntity.ok(tableService.setupTable(request));
    }

    @GetMapping("/list")
    public ResponseEntity<List<TableInfo>> getTableList() {
        return ResponseEntity.ok(tableService.getTableList());
    }

    @PostMapping("/complete/{tableId}")
    public ResponseEntity<Void> completeTable(@PathVariable Long tableId) {
        tableService.completeTable(tableId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/history/{tableId}")
    public ResponseEntity<List<OrderHistory>> getTableHistory(
            @PathVariable Long tableId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(tableService.getTableHistory(tableId, date));
    }
}
