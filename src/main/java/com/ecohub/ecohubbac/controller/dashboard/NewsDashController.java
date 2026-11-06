package com.ecohub.ecohubbac.controller.dashboard;

import com.ecohub.ecohubbac.entity.News;
import com.ecohub.ecohubbac.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/news")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class NewsDashController {

    private final NewsService newsService;
    @GetMapping
    public ResponseEntity<List<News>> getAllNewsForAdmin() {
        return ResponseEntity.ok(newsService.getAllNews());
    }
    @PostMapping
    public ResponseEntity<News> createNews(@RequestBody News news) {
        News savedNews = newsService.createNews(news);
        return ResponseEntity.ok(savedNews);
    }

    @PutMapping("/{id}")
    public ResponseEntity<News> updateNews(@PathVariable Long id, @RequestBody News newsDetails) {
        News updatedNews = newsService.updateNews(id, newsDetails);
        return ResponseEntity.ok(updatedNews);
    }

    // 6. Xəbəri sil (DELETE: /api/news/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.ok("Xəbər uğurla silindi. ID: " + id);
    }
}
