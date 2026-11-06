package com.ecohub.ecohubbac.service.impl;

import com.ecohub.ecohubbac.dto.NewsDetailResponseDTO;
import com.ecohub.ecohubbac.dto.NewsResponseDTO;
import com.ecohub.ecohubbac.entity.News;
import com.ecohub.ecohubbac.entity.NewsCategory;
import com.ecohub.ecohubbac.repository.NewsCategoryRepository;
import com.ecohub.ecohubbac.repository.NewsRepository;
import com.ecohub.ecohubbac.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsCategoryRepository newsCategoryRepository;

    @Override
    public List<NewsResponseDTO> getNewsByCategory(String categoryName) {
        List<News> newsList;

        if (categoryName == null || categoryName.equalsIgnoreCase("Hamısı") || categoryName.isBlank()) {
            newsList = newsRepository.findAll();
        } else {
            newsList = newsRepository.findByCategoryName(categoryName);
        }

        return newsList.stream().map(news -> new NewsResponseDTO(
                news.getId(),
                news.getCategory() != null ? news.getCategory().getName() : "",
                news.getTitle(),
                news.getDescription(),
                news.getDate(),
                news.getImageUrl()
        )).collect(Collectors.toList());
    }

    @Override
    public NewsDetailResponseDTO getNewsById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Xəbər tapılmadı: ID " + id));

        return new NewsDetailResponseDTO(
                news.getId(),
                news.getCategory() != null ? news.getCategory().getName() : "",
                news.getTitle(),
                news.getSubtitle(),
                news.getDescription(),
                news.getContent(),
                news.getDate(),
                news.getImageUrl()
        );
    }

    @Override
    public List<String> getAllCategories() {
        List<NewsCategory> categories = newsCategoryRepository.findAll();
        List<String> categoryNames = categories.stream().map(NewsCategory::getName).collect(Collectors.toList());
        categoryNames.add(0, "Hamısı");
        return categoryNames;
    }

    @Override
    public News createNews(News news) {
        if (news.getCategory() != null && news.getCategory().getId() != null) {
            NewsCategory category = newsCategoryRepository.findById(news.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Kateqoriya tapılmadı"));
            news.setCategory(category);
        }
        return newsRepository.save(news);
    }

    @Override
    public News updateNews(Long id, News newsDetails) {
        News existingNews = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Xəbər tapılmadı: ID " + id));

        existingNews.setTitle(newsDetails.getTitle());
        existingNews.setSubtitle(newsDetails.getSubtitle());
        existingNews.setDescription(newsDetails.getDescription());
        existingNews.setContent(newsDetails.getContent());
        existingNews.setDate(newsDetails.getDate());
        existingNews.setImageUrl(newsDetails.getImageUrl());

        if (newsDetails.getCategory() != null && newsDetails.getCategory().getId() != null) {
            NewsCategory category = newsCategoryRepository.findById(newsDetails.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Kateqoriya tapılmadı"));
            existingNews.setCategory(category);
        }

        return newsRepository.save(existingNews);
    }

    @Override
    public void deleteNews(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Xəbər tapılmadı: ID " + id));
        newsRepository.delete(news);
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }
}