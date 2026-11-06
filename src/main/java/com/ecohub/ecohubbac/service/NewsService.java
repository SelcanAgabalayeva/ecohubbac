package com.ecohub.ecohubbac.service;

import com.ecohub.ecohubbac.dto.NewsDetailResponseDTO;
import com.ecohub.ecohubbac.dto.NewsResponseDTO;
import com.ecohub.ecohubbac.entity.News;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface NewsService {
    List<NewsResponseDTO> getNewsByCategory(String category);
    NewsDetailResponseDTO getNewsById(Long id);
    List<String> getAllCategories();

    // CRUD əməliyyatları
    News createNews(News news);
    News updateNews(Long id, News newsDetails);
    void deleteNews(Long id);

    List<News> getAllNews();
}
