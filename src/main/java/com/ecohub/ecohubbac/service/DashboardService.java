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
        long totalVolunteers = volunteerRepository.count();
        // Baza statuslarınıza uyğun yazın (məsələn: "PENDING", "BEKLIYOR" və ya Azərbaycan dilində "Gözləmədə")
        long newVolunteers = volunteerRepository.countByStatus("PENDING");
        long acceptedVolunteers = volunteerRepository.countByStatus("ACCEPTED");
        long totalProjects = projectRepository.count();

        return new DashboardStatsDTO(
                totalVolunteers,
                newVolunteers,
                acceptedVolunteers,
                totalProjects
        );
    }
}
