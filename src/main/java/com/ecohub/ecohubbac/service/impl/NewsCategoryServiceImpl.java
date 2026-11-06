package com.ecohub.ecohubbac.service.impl;

import com.ecohub.ecohubbac.entity.NewsCategory;
import com.ecohub.ecohubbac.repository.NewsCategoryRepository;
import com.ecohub.ecohubbac.service.NewsCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsCategoryServiceImpl implements NewsCategoryService {

    private final NewsCategoryRepository newsCategoryRepository;

    @Override
    public List<NewsCategory> getAllCategories() {
        return newsCategoryRepository.findAll();
    }

    @Override
    public NewsCategory getCategoryById(Long id) {
        return newsCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Xəbər kateqoriyası tapılmadı: ID " + id));
    }

    @Override
    public NewsCategory createCategory(NewsCategory category) {
        return newsCategoryRepository.save(category);
    }

    @Override
    public NewsCategory updateCategory(Long id, NewsCategory categoryDetails) {
        NewsCategory category = getCategoryById(id);
        category.setName(categoryDetails.getName());
        return newsCategoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        NewsCategory category = getCategoryById(id);
        newsCategoryRepository.delete(category);
    }
}