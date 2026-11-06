package com.ecohub.ecohubbac.service;

import com.ecohub.ecohubbac.dto.ProjectDetailResponseDTO;
import com.ecohub.ecohubbac.dto.ProjectResponseDTO;
import com.ecohub.ecohubbac.entity.Project;

import java.util.List;

public interface ProjectService {
    List<ProjectResponseDTO> getProjectsByCategory(String category);
    ProjectDetailResponseDTO getProjectById(String id);
    List<String> getAllCategories();

    Project createProject(Project project);
    Project updateProject(String id, Project projectDetails);
    void deleteProject(String id);
}
