package com.ecohub.ecohubbac.service;

import com.ecohub.ecohubbac.dto.PartnerRequestDTO;
import com.ecohub.ecohubbac.dto.PartnerResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PartnerService {
    List<PartnerResponseDTO> getAllPartners();
    PartnerResponseDTO getPartnerById(Long id);
    PartnerResponseDTO createPartner(PartnerRequestDTO requestDT, MultipartFile file);
    PartnerResponseDTO updatePartner(Long id, PartnerRequestDTO requestDTO,MultipartFile file);
    void deletePartner(Long id);
}
