package com.ecohub.ecohubbac.controller.dashboard;

import com.ecohub.ecohubbac.dto.ProjectDetailResponseDTO;
import com.ecohub.ecohubbac.dto.ProjectResponseDTO;
import com.ecohub.ecohubbac.entity.Project;
import com.ecohub.ecohubbac.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
@RestController
@RequestMapping("/api/admin/projects")
@RequiredArgsConstructor
public class ProjectDashController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getProject(
            @RequestParam(required = false, defaultValue = "Hamısı") String category) {
        return ResponseEntity.ok(projectService.getProjectsByCategory(category));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDetailResponseDTO> getProjectById(@PathVariable String id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Project> createProject(
            @RequestPart("project") String projectJson, // JSON-u əvvəlcə String kimi qəbul edirik
            @RequestPart(value = "file", required = false) MultipartFile file) {

        Project savedProject;
        try {
            // String-i Project obyektinə çeviririk
            ObjectMapper objectMapper = new ObjectMapper();
            Project project = objectMapper.readValue(projectJson, Project.class);

            savedProject = projectService.createProject(project, file);
        } catch (Exception e) {
            throw new RuntimeException("Project JSON formatı səhvdir: " + e.getMessage());
        }

        return ResponseEntity.ok(savedProject);
    }
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Project> updateProject(
            @PathVariable String id,
            @RequestPart("project") String projectJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        Project updatedProject;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Project projectDetails = objectMapper.readValue(projectJson, Project.class);

            updatedProject = projectService.updateProject(id, projectDetails, file);
        } catch (Exception e) {
            throw new RuntimeException("Project JSON formatı səhvdir: " + e.getMessage());
        }

        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable String id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Layihə uğurla silindi. ID: " + id);
    }
}
