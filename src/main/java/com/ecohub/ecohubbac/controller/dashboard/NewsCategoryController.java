package com.ecohub.ecohubbac.controller.dashboard;
import com.ecohub.ecohubbac.entity.NewsCategory;
import com.ecohub.ecohubbac.service.NewsCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/news-categories")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class NewsCategoryController {

    private final NewsCategoryService newsCategoryService;

    @GetMapping
    public ResponseEntity<List<NewsCategory>> getAllCategories() {
        return ResponseEntity.ok(newsCategoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsCategory> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(newsCategoryService.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<NewsCategory> createCategory(@RequestBody NewsCategory category) {
        NewsCategory savedCategory = newsCategoryService.createCategory(category);
        return ResponseEntity.ok(savedCategory);
    }

    // 4. Mövcud xəbər kateqoriyasını yenilə (PUT: /api/news-categories/{id})
    @PutMapping("/{id}")
    public ResponseEntity<NewsCategory> updateCategory(@PathVariable Long id, @RequestBody NewsCategory categoryDetails) {
        NewsCategory updatedCategory = newsCategoryService.updateCategory(id, categoryDetails);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        newsCategoryService.deleteCategory(id);
        return ResponseEntity.ok("Xəbər kateqoriyası uğurla silindi. ID: " + id);
    }
}
