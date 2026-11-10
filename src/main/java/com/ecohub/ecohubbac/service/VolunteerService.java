package com.ecohub.ecohubbac.service;

import com.ecohub.ecohubbac.dto.CertificateStatsDTO;
import com.ecohub.ecohubbac.dto.VolunteerRequestDTO;
import com.ecohub.ecohubbac.dto.VolunteerResponseDTO;
import com.ecohub.ecohubbac.dto.VolunteerStatsDTO;
import com.ecohub.ecohubbac.entity.Team;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface VolunteerService {
    void createVolunteerApplication(VolunteerRequestDTO requestDTO);
    List<VolunteerResponseDTO> getAllVolunteers();
    VolunteerResponseDTO getVolunteerById(Long id);
    void deleteVolunteer(Long id);
    VolunteerResponseDTO createVolunteer(VolunteerRequestDTO dto);
    VolunteerResponseDTO updateVolunteer(Long id, VolunteerRequestDTO dto);

    Team createTeam(String teamName);
    List<Team> getAllTeams();

     List<VolunteerResponseDTO> getAllApplications();
    VolunteerStatsDTO getVolunteerDashboardStats();
    CertificateStatsDTO getCertificateStats();
    List<VolunteerResponseDTO> getFilteredApplications(String search, String status);

}
