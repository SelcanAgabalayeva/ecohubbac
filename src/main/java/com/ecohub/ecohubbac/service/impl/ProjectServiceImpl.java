package com.ecohub.ecohubbac.service.impl;
import com.ecohub.ecohubbac.dto.ProjectDetailResponseDTO;
import com.ecohub.ecohubbac.dto.ProjectResponseDTO;
import com.ecohub.ecohubbac.entity.Category;
import com.ecohub.ecohubbac.entity.Project;
import com.ecohub.ecohubbac.repository.CategoryRepository;
import com.ecohub.ecohubbac.repository.ProjectRepository;
import com.ecohub.ecohubbac.service.FileService;
import com.ecohub.ecohubbac.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final CategoryRepository categoryRepository;
    private final FileService fileService;

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
    public Project createProject(Project project, MultipartFile file) {
        if (project.getCategory() != null && project.getCategory().getId() != null) {
            Category category = categoryRepository.findById(project.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Kateqoriya tapılmadı"));
            project.setCategory(category);
        }

        // Əgər şəkil yüklənibsə:
        if (file != null && !file.isEmpty()) {
            String imageUrl = fileService.uploadFile(file); // Fayl yükləmə servisiniz
            project.setImageUrl(imageUrl);
        }

        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(String id, Project projectDetails, MultipartFile file) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Layihə tapılmadı: " + id));

        existingProject.setTitle(projectDetails.getTitle());
        existingProject.setSubtitle(projectDetails.getSubtitle());
        existingProject.setDescription(projectDetails.getDescription());
        existingProject.setMission(projectDetails.getMission());
        existingProject.setActivities(projectDetails.getActivities());

        // Əgər istifadəçi yeni şəkil yükləyibsə, Cloudinary-ə yükləyib linki yeniləyirik:
        if (file != null && !file.isEmpty()) {
            String imageUrl = fileService.uploadFile(file);
            existingProject.setImageUrl(imageUrl);
        } else if (projectDetails.getImageUrl() != null) {
            // Əgər fayl göndərilməyibsə amma JSON-da imageUrl gəlibsə onu da saxlaya bilərik
            existingProject.setImageUrl(projectDetails.getImageUrl());
        }

        // Kateqoriya yoxlanışı
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

    public List<ProjectResponseDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();

        List<ProjectResponseDTO> dtoList = new ArrayList<>();
        for (Project project : projects) {
            ProjectResponseDTO dto = new ProjectResponseDTO();
            dto.setNumber(project.getId());

            dto.setTitle(project.getTitle());
            dto.setDescription(project.getDescription());

            if (project.getCategory() != null) {
                dto.setCategory(project.getCategory().getName());
            }

            dto.setImage(project.getImageUrl());

            dtoList.add(dto);
        }

        return dtoList;
    }
}