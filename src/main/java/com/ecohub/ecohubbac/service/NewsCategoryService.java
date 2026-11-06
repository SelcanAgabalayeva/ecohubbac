package com.ecohub.ecohubbac.service;

import com.ecohub.ecohubbac.entity.NewsCategory;

import java.util.List;

public interface NewsCategoryService {
    List<NewsCategory> getAllCategories();
    NewsCategory getCategoryById(Long id);
    NewsCategory createCategory(NewsCategory category);
    NewsCategory updateCategory(Long id, NewsCategory categoryDetails);
    void deleteCategory(Long id);
}
