package com.ecohub.ecohubbac.controller.dashboard;

import com.ecohub.ecohubbac.dto.VolunteerRequestDTO;
import com.ecohub.ecohubbac.dto.VolunteerResponseDTO;
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

    // 1. Əsas "Könüllülər" səhifəsi üçün (Yalnız "Qəbul edildi" olanlar gəlir)
    @GetMapping("/volunteers")
    public ResponseEntity<List<VolunteerResponseDTO>> getAllVolunteers() {
        return ResponseEntity.ok(volunteerService.getAllVolunteers());
    }

    // 2. "Könüllü müraciətləri" səhifəsi üçün (Bütün statusdakılar - Yeni, Baxılır, Rədd və s. gəlir)
    @GetMapping("/applications")
    public ResponseEntity<List<VolunteerResponseDTO>> getAllApplications() {
        return ResponseEntity.ok(volunteerService.getAllApplications());
    }
    // 2. ID-yə görə könüllü
    @GetMapping("/volunteers/{id}")
    public ResponseEntity<VolunteerResponseDTO> getVolunteerById(@PathVariable Long id) {
        return ResponseEntity.ok(volunteerService.getVolunteerById(id));
    }

    // 3. Yeni könüllü əlavə et
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

    // 5. Könüllünü sil
    @DeleteMapping("/volunteers/{id}")
    public ResponseEntity<String> deleteVolunteer(@PathVariable Long id) {
        volunteerService.deleteVolunteer(id);
        return ResponseEntity.ok("Könüllü uğurla silindi. ID: " + id);
    }

    // 6. Bütün komandaları gətir
    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(volunteerService.getAllTeams());
    }

    // 7. Yeni komanda yarat ("+ Yeni komanda yarat" düyməsi üçün)
    @PostMapping("/teams")
    public ResponseEntity<Team> createTeam(@RequestBody Map<String, String> request) {
        String teamName = request.get("name");
        return ResponseEntity.ok(volunteerService.createTeam(teamName));
    }

}
