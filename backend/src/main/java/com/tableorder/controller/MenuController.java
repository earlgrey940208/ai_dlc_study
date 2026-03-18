package com.tableorder.controller;

import com.tableorder.entity.Category;
import com.tableorder.entity.Menu;
import com.tableorder.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(menuService.getCategories());
    }

    @GetMapping("/list")
    public ResponseEntity<List<Menu>> getMenuList(
            @RequestParam(required = false) Long categoryId) {
        return ResponseEntity.ok(menuService.getMenuList(categoryId));
    }
}
