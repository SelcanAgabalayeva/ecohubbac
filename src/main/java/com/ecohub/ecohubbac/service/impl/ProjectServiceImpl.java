package com.ecohub.ecohubbac.service.impl;
import com.ecohub.ecohubbac.dto.ProjectDetailResponseDTO;
import com.ecohub.ecohubbac.dto.ProjectResponseDTO;
import com.ecohub.ecohubbac.entity.Category;
import com.ecohub.ecohubbac.entity.Project;
import com.ecohub.ecohubbac.repository.CategoryRepository;
import com.ecohub.ecohubbac.repository.ProjectRepository;
import com.ecohub.ecohubbac.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProjectResponseDTO> getProjectsByCategory(String categoryName) {
        List<Project> projects;

        if (categoryName == null || categoryName.equalsIgnoreCase("Hamısı") || categoryName.isBlank()) {
            projects = projectRepository.findAll();
        } else {
            projects = projectRepository.findByCategoryName(categoryName);
        }

        return projects.stream().map(project -> new ProjectResponseDTO(
                project.getId(),
                project.getCategory() != null ? project.getCategory().getName() : "",
                project.getTitle(),
                project.getDescription(),
                project.getImageUrl()
        )).collect(Collectors.toList());
    }

    @Override
    public ProjectDetailResponseDTO getProjectById(String id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Layihə tapılmadı: " + id));

        return new ProjectDetailResponseDTO(
                project.getId(),
                project.getCategory() != null ? project.getCategory().getName() : "",
                project.getTitle(),
                project.getSubtitle(),
                project.getImageUrl(),
                project.getDescription(),
                project.getMission(),
                project.getActivities()
        );
    }

    @Override
    public List<String> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<String> categoryNames = categories.stream().map(Category::getName).collect(Collectors.toList());
        categoryNames.add(0, "Hamısı");
        return categoryNames;
    }

    @Override
    public Project createProject(Project project) {
        // Əgər kateqoriya ID-si göndərilibsə, bazadan həmin kateqoriyanı tapıb əlavə edirik
        if (project.getCategory() != null && project.getCategory().getId() != null) {
            Category category = categoryRepository.findById(project.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Kateqoriya tapılmadı"));
            project.setCategory(category);
        }
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(String id, Project projectDetails) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Layihə tapılmadı: " + id));

        existingProject.setTitle(projectDetails.getTitle());
        existingProject.setSubtitle(projectDetails.getSubtitle());
        existingProject.setDescription(projectDetails.getDescription());
        existingProject.setMission(projectDetails.getMission());
        existingProject.setImageUrl(projectDetails.getImageUrl());
        existingProject.setActivities(projectDetails.getActivities());

        if (projectDetails.getCategory() != null && projectDetails.getCategory().getId() != null) {
            Category category = categoryRepository.findById(projectDetails.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Kateqoriya tapılmadı"));
            existingProject.setCategory(category);
        }

        return projectRepository.save(existingProject);
    }

    @Override
    public void deleteProject(String id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Layihə tapılmadı: " + id));
        projectRepository.delete(project);
    }
}