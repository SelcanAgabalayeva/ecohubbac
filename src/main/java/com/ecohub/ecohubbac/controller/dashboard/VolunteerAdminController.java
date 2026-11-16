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

    // Bütün qəbul edilmiş könüllülər
    @GetMapping
    public ResponseEntity<List<VolunteerResponseDTO>> getAllVolunteers() {
        return ResponseEntity.ok(volunteerService.getAllVolunteers());
    }

    // Bütün müraciətlər və filtrləmə
    @GetMapping("/applications")
    public ResponseEntity<List<VolunteerResponseDTO>> getApplications(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status) {

        return ResponseEntity.ok(
                volunteerService.getFilteredApplications(search, status)
        );
    }

    // ID-yə görə könüllü və ya müraciət
    @GetMapping("/{id}")
    public ResponseEntity<VolunteerResponseDTO> getVolunteerById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                volunteerService.getVolunteerById(id)
        );
    }

    // Yeni könüllü yarat
    @PostMapping
    public ResponseEntity<VolunteerResponseDTO> createVolunteer(
            @RequestBody VolunteerRequestDTO requestDTO) {

        return ResponseEntity.ok(
                volunteerService.createVolunteer(requestDTO)
        );
    }

    // Könüllü məlumatlarını yenilə
    @PutMapping("/{id}")
    public ResponseEntity<VolunteerResponseDTO> updateVolunteer(
            @PathVariable Long id,
            @RequestBody VolunteerRequestDTO requestDTO) {

        return ResponseEntity.ok(
                volunteerService.updateVolunteer(id, requestDTO)
        );
    }

    // Könüllünü sil
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVolunteer(
            @PathVariable Long id) {

        volunteerService.deleteVolunteer(id);

        return ResponseEntity.ok(
                "Könüllü uğurla silindi. ID: " + id
        );
    }

    // Könüllünün statusunu dəyiş
    @PatchMapping("/{id}/status")
    public ResponseEntity<VolunteerResponseDTO> updateVolunteerStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                volunteerService.updateVolunteerStatus(id, status)
        );
    }

    // Könüllünü komandaya təyin et
    @PutMapping("/{id}/team")
    public ResponseEntity<VolunteerResponseDTO> assignVolunteerToTeam(
            @PathVariable Long id,
            @RequestParam Long teamId) {

        return ResponseEntity.ok(
                volunteerService.assignVolunteerToTeam(id, teamId)
        );
    }

    // Könüllünün aktivliyini dəyiş
    @PatchMapping("/{id}/activity")
    public ResponseEntity<VolunteerResponseDTO> updateVolunteerActivity(
            @PathVariable Long id,
            @RequestParam boolean active) {

        return ResponseEntity.ok(
                volunteerService.updateVolunteerActivity(id, active)
        );
    }

    // Komandalar
    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(volunteerService.getAllTeams());
    }

    @PostMapping("/teams")
    public ResponseEntity<Team> createTeam(
            @RequestBody Map<String, String> request) {

        String teamName = request.get("name");

        return ResponseEntity.ok(
                volunteerService.createTeam(teamName)
        );
    }

    // Dashboard statistikası
    @GetMapping("/stats")
    public ResponseEntity<VolunteerStatsDTO> getDashboardStats() {
        return ResponseEntity.ok(
                volunteerService.getVolunteerDashboardStats()
        );
    }

    // Sertifikat siyahısı
    @GetMapping("/certificates/list")
    public ResponseEntity<List<VolunteerResponseDTO>> getAllVolunteersForCertificates() {
        return ResponseEntity.ok(volunteerService.getAllVolunteers());
    }

    // Sertifikat statistikası
    @GetMapping("/certificates/stats")
    public ResponseEntity<CertificateStatsDTO> getCertificateStats() {
        return ResponseEntity.ok(
                volunteerService.getCertificateStats()
        );
    }

    // Sertifikat ver
    @PutMapping("/{id}/issue-certificate")
    public ResponseEntity<VolunteerResponseDTO> issueCertificate(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                volunteerService.issueCertificate(id)
        );
    }
}