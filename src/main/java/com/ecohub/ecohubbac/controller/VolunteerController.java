package com.ecohub.ecohubbac.controller;

import com.ecohub.ecohubbac.dto.VolunteerRequestDTO;
import com.ecohub.ecohubbac.service.VolunteerService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/volunteers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VolunteerController {

    private final VolunteerService volunteerService;

    @PostMapping("/apply")
    public ResponseEntity<String> submitApplication(
            @RequestBody VolunteerRequestDTO requestDTO
    ) {

        volunteerService.createVolunteerApplication(requestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Könüllü müraciəti uğurla göndərildi.");
    }
}