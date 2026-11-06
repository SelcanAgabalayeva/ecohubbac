package com.ecohub.ecohubbac.service;

import com.ecohub.ecohubbac.dto.PartnerRequestDTO;
import com.ecohub.ecohubbac.dto.PartnerResponseDTO;
import java.util.List;

public interface PartnerService {
    List<PartnerResponseDTO> getAllPartners();
    PartnerResponseDTO getPartnerById(Long id);
    PartnerResponseDTO createPartner(PartnerRequestDTO requestDTO);
    PartnerResponseDTO updatePartner(Long id, PartnerRequestDTO requestDTO);
    void deletePartner(Long id);
}
