package com.ecohub.ecohubbac.controller.dashboard;

import com.ecohub.ecohubbac.dto.CertificateStatsDTO;
import com.ecohub.ecohubbac.dto.VolunteerRequestDTO;
import com.ecohub.ecohubbac.dto.VolunteerResponseDTO;
import com.ecohub.ecohubbac.dto.VolunteerStatsDTO;
import com.ecohub.ecohubbac.entity.Team;
import com.ecohub.ecohubbac.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/volunteers")
@RequiredArgsConstructor
public class VolunteerAdminController {

    private final VolunteerService volunteerService;


    @GetMapping("/volunteers")
    public ResponseEntity<List<VolunteerResponseDTO>> getAllVolunteers() {
        return ResponseEntity.ok(volunteerService.getAllVolunteers());
    }

    @GetMapping("/applications")
    public ResponseEntity<List<VolunteerResponseDTO>> getApplications(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status) {
        return ResponseEntity.ok(volunteerService.getFilteredApplications(search, status));
    }

    @GetMapping("/volunteers/{id}")
    public ResponseEntity<VolunteerResponseDTO> getVolunteerById(@PathVariable Long id) {
        return ResponseEntity.ok(volunteerService.getVolunteerById(id));
    }


    @PostMapping("/volunteers")
    public ResponseEntity<VolunteerResponseDTO> createVolunteer(@RequestBody VolunteerRequestDTO requestDTO) {
        return ResponseEntity.ok(volunteerService.createVolunteer(requestDTO));
    }
    @PutMapping("/volunteers/{id}")
    public ResponseEntity<VolunteerResponseDTO> updateVolunteer(
            @PathVariable Long id,
            @RequestBody VolunteerRequestDTO requestDTO) {
        return ResponseEntity.ok(volunteerService.updateVolunteer(id, requestDTO));
    }

    @DeleteMapping("/volunteers/{id}")
    public ResponseEntity<String> deleteVolunteer(@PathVariable Long id) {
        volunteerService.deleteVolunteer(id);
        return ResponseEntity.ok("Könüllü uğurla silindi. ID: " + id);
    }


    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(volunteerService.getAllTeams());
    }


    @PostMapping("/teams")
    public ResponseEntity<Team> createTeam(@RequestBody Map<String, String> request) {
        String teamName = request.get("name");
        return ResponseEntity.ok(volunteerService.createTeam(teamName));
    }

    @GetMapping("/stats")
    public ResponseEntity<VolunteerStatsDTO> getDashboardStats() {
        return ResponseEntity.ok(volunteerService.getVolunteerDashboardStats());
    }
    @GetMapping("/certificates/list")
    public ResponseEntity<List<VolunteerResponseDTO>> getAllVolunteersForCertificates() {
        return ResponseEntity.ok(volunteerService.getAllVolunteers());
    }

    @GetMapping("/certificates/stats")
    public ResponseEntity<CertificateStatsDTO> getCertificateStats() {
        return ResponseEntity.ok(volunteerService.getCertificateStats());
    }

}
