package com.ecohub.ecohubbac.service;

import com.ecohub.ecohubbac.dto.ProjectDetailResponseDTO;
import com.ecohub.ecohubbac.dto.ProjectResponseDTO;
import com.ecohub.ecohubbac.entity.Project;
import org.jspecify.annotations.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectService {
    List<ProjectResponseDTO> getProjectsByCategory(String category);
    ProjectDetailResponseDTO getProjectById(String id);
    List<String> getAllCategories();

    Project createProject(Project project, MultipartFile file);
    Project updateProject(String id, Project projectDetails, MultipartFile file);
    void deleteProject(String id);

     List<ProjectResponseDTO> getAllProjects();
}

