package com.ecohub.ecohubbac.controller.dashboard;

import com.ecohub.ecohubbac.dto.ProjectResponseDTO;
import com.ecohub.ecohubbac.entity.Project;
import com.ecohub.ecohubbac.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project savedProject = projectService.createProject(project);
        return ResponseEntity.ok(savedProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable String id, @RequestBody Project projectDetails) {
        Project updatedProject = projectService.updateProject(id, projectDetails);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable String id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Layihə uğurla silindi. ID: " + id);
    }
}
