package com.ecohub.ecohubbac.service.impl;

import com.ecohub.ecohubbac.dto.PartnerRequestDTO;
import com.ecohub.ecohubbac.dto.PartnerResponseDTO;
import com.ecohub.ecohubbac.entity.Partner;
import com.ecohub.ecohubbac.repository.PartnerRepository;
import com.ecohub.ecohubbac.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;

    @Override
    public List<PartnerResponseDTO> getAllPartners() {
        return partnerRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PartnerResponseDTO getPartnerById(Long id) {
        Partner partner = partnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tərəfdaş tapılmadı: ID " + id));
        return mapToResponseDTO(partner);
    }

    @Override
    public PartnerResponseDTO createPartner(PartnerRequestDTO requestDTO) {
        Partner partner = new Partner();
        partner.setName(requestDTO.getName());
        partner.setLogoUrl(requestDTO.getLogoUrl());
        partner.setWebsiteUrl(requestDTO.getWebsiteUrl());

        Partner savedPartner = partnerRepository.save(partner);
        return mapToResponseDTO(savedPartner);
    }

    @Override
    public PartnerResponseDTO updatePartner(Long id, PartnerRequestDTO requestDTO) {
        Partner partner = partnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tərəfdaş tapılmadı: ID " + id));

        partner.setName(requestDTO.getName());
        partner.setLogoUrl(requestDTO.getLogoUrl());
        partner.setWebsiteUrl(requestDTO.getWebsiteUrl());

        Partner updatedPartner = partnerRepository.save(partner);
        return mapToResponseDTO(updatedPartner);
    }

    @Override
    public void deletePartner(Long id) {
        Partner partner = partnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tərəfdaş tapılmadı: ID " + id));
        partnerRepository.delete(partner);
    }

    private PartnerResponseDTO mapToResponseDTO(Partner partner) {
        PartnerResponseDTO dto = new PartnerResponseDTO();
        dto.setId(partner.getId());
        dto.setName(partner.getName());
        dto.setLogoUrl(partner.getLogoUrl());
        dto.setWebsiteUrl(partner.getWebsiteUrl());
        return dto;
    }
}
