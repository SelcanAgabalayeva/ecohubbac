package com.ecohub.ecohubbac.service;
import com.ecohub.ecohubbac.dto.DashboardStatsDTO;
import com.ecohub.ecohubbac.dto.VolunteerResponseDTO;
import com.ecohub.ecohubbac.entity.Volunteer;
import com.ecohub.ecohubbac.repository.ProjectRepository;
import com.ecohub.ecohubbac.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final VolunteerRepository volunteerRepository;
    private final ProjectRepository projectRepository;


    public DashboardStatsDTO getDashboardStats() {
        long totalVolunteers = volunteerRepository.count(); // Ümumi müraciətlər
        long newVolunteers = volunteerRepository.countByStatus("Yeni"); // Yeni müraciətlər
        long acceptedVolunteers = volunteerRepository.countByStatus("Qəbul edildi"); // Qəbul edilənlər
        long totalProjects = projectRepository.count(); // Layihələr

        return new DashboardStatsDTO(
                totalVolunteers,
                newVolunteers,
                acceptedVolunteers,
                totalProjects
        );
    }

    public List<VolunteerResponseDTO> getLatestVolunteerApplications() {
        List<Volunteer> latestVolunteers = volunteerRepository.findTop5ByOrderByIdDesc();

        return latestVolunteers.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private VolunteerResponseDTO mapToDto(Volunteer v) {
        VolunteerResponseDTO dto = new VolunteerResponseDTO();
        dto.setId(v.getId());
        dto.setFirstName(v.getFirstName());
        dto.setLastName(v.getLastName());
        dto.setEmail(v.getEmail());
        dto.setPhone(v.getPhone());
        dto.setStatus(v.getStatus());
        dto.setCreatedAt(v.getCreatedAt());
        return dto;
    }
}
