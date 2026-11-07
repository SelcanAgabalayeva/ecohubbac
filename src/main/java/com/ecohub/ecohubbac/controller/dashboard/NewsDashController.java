package com.ecohub.ecohubbac.controller.dashboard;

import com.ecohub.ecohubbac.entity.News;
import com.ecohub.ecohubbac.service.NewsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<News> createNews(
            @RequestPart("news") String newsJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        News savedNews;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            News news = objectMapper.readValue(newsJson, News.class);
            savedNews = newsService.createNews(news, file);
        } catch (Exception e) {
            throw new RuntimeException("News JSON formatı səhvdir: " + e.getMessage());
        }

        return ResponseEntity.ok(savedNews);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<News> updateNews(
            @PathVariable Long id,
            @RequestPart("news") String newsJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        News updatedNews;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            News newsDetails = objectMapper.readValue(newsJson, News.class);
            updatedNews = newsService.updateNews(id, newsDetails, file);
        } catch (Exception e) {
            throw new RuntimeException("News JSON formatı səhvdir: " + e.getMessage());
        }

        return ResponseEntity.ok(updatedNews);
    }

    // 6. Xəbəri sil (DELETE: /api/news/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.ok("Xəbər uğurla silindi. ID: " + id);
    }
}
