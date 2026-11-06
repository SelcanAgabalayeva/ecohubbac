package com.ecohub.ecohubbac.controller;

import com.ecohub.ecohubbac.dto.PartnerResponseDTO;
import com.ecohub.ecohubbac.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partners")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;

    // Bütün tərəfdaşları səhifədə göstərmək üçün (GET: /api/partners - Hamıya açıqdır)
    @GetMapping
    public ResponseEntity<List<PartnerResponseDTO>> getAllPartners() {
        return ResponseEntity.ok(partnerService.getAllPartners());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartnerResponseDTO> getPartnerById(@PathVariable Long id) {
        return ResponseEntity.ok(partnerService.getPartnerById(id));
    }
}
