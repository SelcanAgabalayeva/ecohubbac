package com.ecohub.ecohubbac.service;
import com.ecohub.ecohubbac.dto.DashboardStatsDTO;
import com.ecohub.ecohubbac.repository.ProjectRepository;
import com.ecohub.ecohubbac.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
