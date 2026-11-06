package com.ecohub.ecohubbac.controller;

import com.ecohub.ecohubbac.dto.VolunteerRequestDTO;
import com.ecohub.ecohubbac.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/volunteers")
@RequiredArgsConstructor
public class VolunteerController {

    private final VolunteerService volunteerService;

    // İstifadəçi formanı doldurub göndərdikdə işləyir (POST: /api/volunteers - Hamıya açıqdır)
    @PostMapping
    public ResponseEntity<String> createApplication(@RequestBody VolunteerRequestDTO requestDTO) {
        volunteerService.createVolunteerApplication(requestDTO);
        return ResponseEntity.ok("Könüllü müraciətiniz uğurla göndərildi!");
    }
}
