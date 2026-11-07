package com.ecohub.ecohubbac.service;

import com.ecohub.ecohubbac.dto.VolunteerRequestDTO;
import com.ecohub.ecohubbac.dto.VolunteerResponseDTO;
import java.util.List;

public interface VolunteerService {
    void createVolunteerApplication(VolunteerRequestDTO requestDTO);
    List<VolunteerResponseDTO> getAllVolunteers();
    VolunteerResponseDTO getVolunteerById(Long id);
    void deleteVolunteer(Long id);

    void updateStatus(Long id, String status);

}
