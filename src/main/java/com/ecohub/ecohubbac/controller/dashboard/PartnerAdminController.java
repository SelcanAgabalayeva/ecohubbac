package com.ecohub.ecohubbac.controller.dashboard;

import com.ecohub.ecohubbac.dto.PartnerRequestDTO;
import com.ecohub.ecohubbac.dto.PartnerResponseDTO;
import com.ecohub.ecohubbac.service.PartnerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/admin/partners")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PartnerAdminController {

    private final PartnerService partnerService;

    @GetMapping
    public ResponseEntity<List<PartnerResponseDTO>> getAllPartnersForAdmin() {
        return ResponseEntity.ok(partnerService.getAllPartners());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PartnerResponseDTO> getPartnerById(@PathVariable Long id) {
        return ResponseEntity.ok(partnerService.getPartnerById(id));
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PartnerResponseDTO> createPartner(
            @RequestPart("partner") String partnerJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        PartnerResponseDTO response;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            PartnerRequestDTO requestDTO = objectMapper.readValue(partnerJson, PartnerRequestDTO.class);
            response = partnerService.createPartner(requestDTO, file);
        } catch (Exception e) {
            throw new RuntimeException("Partner JSON formatı səhvdir: " + e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PartnerResponseDTO> updatePartner(
            @PathVariable Long id,
            @RequestPart("partner") String partnerJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        PartnerResponseDTO response;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            PartnerRequestDTO requestDTO = objectMapper.readValue(partnerJson, PartnerRequestDTO.class);
            response = partnerService.updatePartner(id, requestDTO, file);
        } catch (Exception e) {
            throw new RuntimeException("Partner JSON formatı səhvdir: " + e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePartner(@PathVariable Long id) {
        partnerService.deletePartner(id);
        return ResponseEntity.ok("Tərəfdaş uğurla silindi. ID: " + id);
    }
}
