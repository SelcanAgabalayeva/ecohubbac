package com.ecohub.ecohubbac.controller.dashboard;

import com.ecohub.ecohubbac.dto.PartnerRequestDTO;
import com.ecohub.ecohubbac.dto.PartnerResponseDTO;
import com.ecohub.ecohubbac.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public ResponseEntity<PartnerResponseDTO> createPartner(@RequestBody PartnerRequestDTO requestDTO) {
        return ResponseEntity.ok(partnerService.createPartner(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartnerResponseDTO> updatePartner(@PathVariable Long id, @RequestBody PartnerRequestDTO requestDTO) {
        return ResponseEntity.ok(partnerService.updatePartner(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePartner(@PathVariable Long id) {
        partnerService.deletePartner(id);
        return ResponseEntity.ok("Tərəfdaş uğurla silindi. ID: " + id);
    }
}
