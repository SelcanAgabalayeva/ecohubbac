package com.ecohub.ecohubbac.controller.dashboard;

import com.ecohub.ecohubbac.dto.VolunteerResponseDTO;
import com.ecohub.ecohubbac.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/volunteers")
@RequiredArgsConstructor
public class VolunteerAdminController {

    private final VolunteerService volunteerService;

    @GetMapping
    public ResponseEntity<List<VolunteerResponseDTO>> getAllVolunteers() {
        return ResponseEntity.ok(volunteerService.getAllVolunteers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VolunteerResponseDTO> getVolunteerById(@PathVariable Long id) {
        return ResponseEntity.ok(volunteerService.getVolunteerById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVolunteer(@PathVariable Long id) {
        volunteerService.deleteVolunteer(id);
        return ResponseEntity.ok("Müraciət uğurla silindi. ID: " + id);
    }
}
