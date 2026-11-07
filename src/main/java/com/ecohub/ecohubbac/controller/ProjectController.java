package com.ecohub.ecohubbac.controller;
import com.ecohub.ecohubbac.dto.ProjectDetailResponseDTO;
import com.ecohub.ecohubbac.dto.ProjectResponseDTO;
import com.ecohub.ecohubbac.entity.Project;
import com.ecohub.ecohubbac.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*") // React bağlantısı üçün CORS
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    @GetMapping("/all")
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjects() {
        List<ProjectResponseDTO> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }
    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getProjects(
            @RequestParam(required = false, defaultValue = "Hamısı") String category) {
        return ResponseEntity.ok(projectService.getProjectsByCategory(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDetailResponseDTO> getProjectById(@PathVariable String id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        return ResponseEntity.ok(projectService.getAllCategories());
    }


}