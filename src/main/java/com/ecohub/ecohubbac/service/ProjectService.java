package com.ecohub.ecohubbac.service;

import com.ecohub.ecohubbac.dto.ProjectDetailResponseDTO;
import com.ecohub.ecohubbac.dto.ProjectResponseDTO;
import com.ecohub.ecohubbac.entity.Project;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectService {
    List<ProjectResponseDTO> getProjectsByCategory(String category);

    ProjectDetailResponseDTO getProjectById(Long id); // String -> Long

    List<String> getAllCategories();

    Project createProject(Project project, MultipartFile file);

    Project updateProject(Long id, Project projectDetails, MultipartFile file); // String -> Long

    void deleteProject(Long id); // String -> Long

    List<ProjectResponseDTO> getAllProjects();
}