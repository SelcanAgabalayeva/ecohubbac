package com.ecohub.ecohubbac.service.impl;

import com.ecohub.ecohubbac.dto.PartnerRequestDTO;
import com.ecohub.ecohubbac.dto.PartnerResponseDTO;
import com.ecohub.ecohubbac.entity.Partner;
import com.ecohub.ecohubbac.repository.PartnerRepository;
import com.ecohub.ecohubbac.service.FileService;
import com.ecohub.ecohubbac.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;
    private final FileService fileService;

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
    public PartnerResponseDTO createPartner(PartnerRequestDTO requestDTO, MultipartFile file) {
        Partner partner = new Partner();
        partner.setName(requestDTO.getName());
        partner.setWebsiteUrl(requestDTO.getWebsiteUrl()); // 👈 websiteUrl əlavə olundu

        // Şəkil yüklənməsi (Cloudinary və ya URL)
        if (file != null && !file.isEmpty()) {
            String logoUrl = fileService.uploadFile(file);
            partner.setLogoUrl(logoUrl);
        } else {
            partner.setLogoUrl(requestDTO.getLogoUrl());
        }

        Partner savedPartner = partnerRepository.save(partner);
        return mapToResponseDTO(savedPartner);
    }

    @Override
    public PartnerResponseDTO updatePartner(Long id, PartnerRequestDTO requestDTO, MultipartFile file) {
        Partner existingPartner = partnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tərəfdaş tapılmadı: ID " + id));

        existingPartner.setName(requestDTO.getName());
        existingPartner.setWebsiteUrl(requestDTO.getWebsiteUrl()); // 👈 websiteUrl yenilənməsi

        // Əgər yeni şəkil yüklənibsə Cloudinary-ə yüklə, yoxdursa DTO-dan gələni və ya köhnəni saxla
        if (file != null && !file.isEmpty()) {
            String logoUrl = fileService.uploadFile(file);
            existingPartner.setLogoUrl(logoUrl);
        } else if (requestDTO.getLogoUrl() != null && !requestDTO.getLogoUrl().isBlank()) {
            existingPartner.setLogoUrl(requestDTO.getLogoUrl());
        }
        // Əgər həm file yoxdur, həm də DTO-da logoUrl null-dursa, avtomatik existingPartner-dəki köhnə logoUrl qalır.

        Partner updatedPartner = partnerRepository.save(existingPartner);
        return mapToResponseDTO(updatedPartner);
    }

    @Override
    public void deletePartner(Long id) {
        Partner partner = partnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tərəfdaş tapılmadı: ID " + id));
        partnerRepository.delete(partner);
    }

    // Tək və universal mapping metodu (həmçinin websiteUrl-i də əhatə edir)
    private PartnerResponseDTO mapToResponseDTO(Partner partner) {
        PartnerResponseDTO dto = new PartnerResponseDTO();
        dto.setId(partner.getId());
        dto.setName(partner.getName());
        dto.setLogoUrl(partner.getLogoUrl());
        dto.setWebsiteUrl(partner.getWebsiteUrl());
        return dto;
    }
}