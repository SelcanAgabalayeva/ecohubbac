package com.ecohub.ecohubbac.controller;

import com.ecohub.ecohubbac.dto.NewsDetailResponseDTO;
import com.ecohub.ecohubbac.dto.NewsResponseDTO;
import com.ecohub.ecohubbac.entity.News;
import com.ecohub.ecohubbac.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    // 1. Xəbərləri kateqoriyaya görə gətir (GET: /api/news?category=Ekologiya)
    @GetMapping
    public ResponseEntity<List<NewsResponseDTO>> getNews(
            @RequestParam(required = false, defaultValue = "Hamısı") String category) {
        return ResponseEntity.ok(newsService.getNewsByCategory(category));
    }

    // 2. Tək bir xəbərin detallarını gətir (GET: /api/news/{id})
    @GetMapping("/{id}")
    public ResponseEntity<NewsDetailResponseDTO> getNewsById(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.getNewsById(id));
    }

    // 3. Kateqoriyalar siyahısını gətir (GET: /api/news/categories)
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        return ResponseEntity.ok(newsService.getAllCategories());
    }


}
