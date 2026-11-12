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
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class VolunteerAdminController {

    private final VolunteerService volunteerService;

    // GET: /api/admin/volunteers
    @GetMapping
    public ResponseEntity<List<VolunteerResponseDTO>> getAllVolunteers() {
        return ResponseEntity.ok(volunteerService.getAllVolunteers());
    }

    // GET: /api/admin/volunteers/applications
    @GetMapping("/applications")
    public ResponseEntity<List<VolunteerResponseDTO>> getApplications(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status) {
        return ResponseEntity.ok(volunteerService.getFilteredApplications(search, status));
    }

    // GET: /api/admin/volunteers/{id}
    @GetMapping("/{id}")
    public ResponseEntity<VolunteerResponseDTO> getVolunteerById(@PathVariable Long id) {
        return ResponseEntity.ok(volunteerService.getVolunteerById(id));
    }

    // 🟢 DÜZƏLDİldİ: POST: /api/admin/volunteers (Artıq sonundakı /volunteers silindi)
    @PostMapping
    public ResponseEntity<VolunteerResponseDTO> createVolunteer(@RequestBody VolunteerRequestDTO requestDTO) {
        return ResponseEntity.ok(volunteerService.createVolunteer(requestDTO));
    }

    // PUT: /api/admin/volunteers/{id}
    @PutMapping("/{id}")
    public ResponseEntity<VolunteerResponseDTO> updateVolunteer(
            @PathVariable Long id,
            @RequestBody VolunteerRequestDTO requestDTO) {
        return ResponseEntity.ok(volunteerService.updateVolunteer(id, requestDTO));
    }

    // DELETE: /api/admin/volunteers/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVolunteer(@PathVariable Long id) {
        volunteerService.deleteVolunteer(id);
        return ResponseEntity.ok("Könüllü uğurla silindi. ID: " + id);
    }

    // Komanda dəyişmək -> PUT: /api/admin/volunteers/{id}/team
    @PutMapping("/{id}/team")
    public ResponseEntity<VolunteerResponseDTO> assignVolunteerToTeam(
            @PathVariable Long id,
            @RequestParam Long teamId) {
        return ResponseEntity.ok(volunteerService.assignVolunteerToTeam(id, teamId));
    }

    // Fəaliyyəti bitirmək / Aktivliyi dəyişmək -> PATCH: /api/admin/volunteers/{id}/activity
    @PatchMapping("/{id}/activity")
    public ResponseEntity<VolunteerResponseDTO> updateVolunteerActivity(
            @PathVariable Long id,
            @RequestParam boolean active) {
        return ResponseEntity.ok(volunteerService.updateVolunteerActivity(id, active));
    }

    // GET: /api/admin/volunteers/teams
    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(volunteerService.getAllTeams());
    }

    // POST: /api/admin/volunteers/teams
    @PostMapping("/teams")
    public ResponseEntity<Team> createTeam(@RequestBody Map<String, String> request) {
        String teamName = request.get("name");
        return ResponseEntity.ok(volunteerService.createTeam(teamName));
    }

    // GET: /api/admin/volunteers/stats
    @GetMapping("/stats")
    public ResponseEntity<VolunteerStatsDTO> getDashboardStats() {
        return ResponseEntity.ok(volunteerService.getVolunteerDashboardStats());
    }

    // GET: /api/admin/volunteers/certificates/list
    @GetMapping("/certificates/list")
    public ResponseEntity<List<VolunteerResponseDTO>> getAllVolunteersForCertificates() {
        return ResponseEntity.ok(volunteerService.getAllVolunteers());
    }

    // GET: /api/admin/volunteers/certificates/stats
    @GetMapping("/certificates/stats")
    public ResponseEntity<CertificateStatsDTO> getCertificateStats() {
        return ResponseEntity.ok(volunteerService.getCertificateStats());
    }

    // PUT: /api/admin/volunteers/{id}/issue-certificate
    @PutMapping("/{id}/issue-certificate")
    public ResponseEntity<VolunteerResponseDTO> issueCertificate(@PathVariable Long id) {
        return ResponseEntity.ok(volunteerService.issueCertificate(id));
    }
}