package com.tableorder.service;

import com.tableorder.entity.Category;
import com.tableorder.entity.Menu;
import com.tableorder.repository.CategoryRepository;
import com.tableorder.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAllByOrderBySortOrderAsc();
    }

    public List<Menu> getMenuList(Long categoryId) {
        if (categoryId != null) {
            return menuRepository.findByCategoryId(categoryId);
        }
        return menuRepository.findAll();
    }
}
