package com.ecohub.ecohubbac.service.impl;

import com.ecohub.ecohubbac.dto.CertificateStatsDTO;
import com.ecohub.ecohubbac.dto.VolunteerRequestDTO;
import com.ecohub.ecohubbac.dto.VolunteerResponseDTO;
import com.ecohub.ecohubbac.dto.VolunteerStatsDTO;
import com.ecohub.ecohubbac.entity.Team;
import com.ecohub.ecohubbac.entity.Volunteer;
import com.ecohub.ecohubbac.repository.TeamRepository;
import com.ecohub.ecohubbac.repository.VolunteerRepository;
import com.ecohub.ecohubbac.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerRepository volunteerRepository;
    private final TeamRepository teamRepository;

    @Override
    public void createVolunteerApplication(VolunteerRequestDTO requestDTO) {
        Volunteer volunteer = new Volunteer();
        volunteer.setFirstName(requestDTO.getFirstName());
        volunteer.setLastName(requestDTO.getLastName());
        volunteer.setFatherName(requestDTO.getFatherName());
        volunteer.setGender(requestDTO.getGender());
        volunteer.setBirthDate(requestDTO.getBirthDate());
        volunteer.setEmail(requestDTO.getEmail());
        volunteer.setPhone(requestDTO.getPhone());
        volunteer.setAddress(requestDTO.getAddress());
        volunteer.setCitizenship(requestDTO.getCitizenship());
        volunteer.setCitizenshipOther(requestDTO.getCitizenshipOther());
        volunteer.setEducation(requestDTO.getEducation());
        volunteer.setEducationOther(requestDTO.getEducationOther());
        volunteer.setInstitution(requestDTO.getInstitution());
        volunteer.setSpecialty(requestDTO.getSpecialty());

        volunteer.setLanguages(requestDTO.getLanguages());
        volunteer.setComputerSkills(requestDTO.getComputerSkills());
        volunteer.setCanva(requestDTO.getCanva());
        volunteer.setInterests(requestDTO.getInterests());
        volunteer.setInterestOther(requestDTO.getInterestOther());
        volunteer.setExperience(requestDTO.getExperience());
        volunteer.setExpectations(requestDTO.getExpectations());
        volunteer.setInstagram(requestDTO.getInstagram());
        volunteer.setFacebook(requestDTO.getFacebook());
        volunteer.setLinkedin(requestDTO.getLinkedin());
        volunteer.setExtraNotes(requestDTO.getExtraNotes());

        volunteerRepository.save(volunteer);
    }

    @Override
    public List<VolunteerResponseDTO> getAllVolunteers() {
        return volunteerRepository.findByStatus("Qəbul edildi").stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Əgər "Könüllü müraciətləri" səhifəsi üçün BÜTÜN müraciətləri (Yeni, Baxılır və s.) çəkmək lazımdırsa, bu əlavə metodu yaradın:
    public List<VolunteerResponseDTO> getAllApplications() {
        return volunteerRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ID-yə görə tək könüllü
    public VolunteerResponseDTO getVolunteerById(Long id) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Könüllü tapılmadı: ID " + id));
        return mapToDto(volunteer);
    }

    // Yeni könüllü yarat
    public VolunteerResponseDTO createVolunteer(VolunteerRequestDTO dto) {
        Volunteer volunteer = new Volunteer();
        updateEntityFromDto(volunteer, dto);
        return mapToDto(volunteerRepository.save(volunteer));
    }

    // Könüllü məlumatlarını yenilə (Komanda dəyişmək, status dəyişmək, sertifikat vermək və s.)
    public VolunteerResponseDTO updateVolunteer(Long id, VolunteerRequestDTO dto) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Könüllü tapılmadı: ID " + id));

        updateEntityFromDto(volunteer, dto);
        return mapToDto(volunteerRepository.save(volunteer));
    }

    // Könüllünü sil
    public void deleteVolunteer(Long id) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Könüllü tapılmadı: ID " + id));
        volunteerRepository.delete(volunteer);
    }

    // Komandalar üzrə əməliyyatlar
    public Team createTeam(String teamName) {
        Team team = new Team();
        team.setName(teamName);
        return teamRepository.save(team);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    // Dashboard Statistika kartları üçün
    public long getActiveVolunteersCount() {
        return volunteerRepository.findAll().stream().filter(Volunteer::isActive).count();
    }

    public long getTotalTeamsCount() {
        return teamRepository.count();
    }

    public long getCertificatePendingCount() {
        return volunteerRepository.findAll().stream().filter(v -> !v.isCertificateIssued()).count();
    }

    // ---------------- HELPER METHODS ----------------

    private void updateEntityFromDto(Volunteer volunteer, VolunteerRequestDTO dto) {
        volunteer.setFirstName(dto.getFirstName());
        volunteer.setLastName(dto.getLastName());
        volunteer.setFatherName(dto.getFatherName());
        volunteer.setGender(dto.getGender());
        volunteer.setBirthDate(dto.getBirthDate());
        volunteer.setEmail(dto.getEmail());
        volunteer.setPhone(dto.getPhone());
        volunteer.setAddress(dto.getAddress());
        volunteer.setCitizenship(dto.getCitizenship());
        volunteer.setCitizenshipOther(dto.getCitizenshipOther());
        volunteer.setEducation(dto.getEducation());
        volunteer.setEducationOther(dto.getEducationOther());
        volunteer.setInstitution(dto.getInstitution());
        volunteer.setSpecialty(dto.getSpecialty());
        volunteer.setLanguages(dto.getLanguages());
        volunteer.setComputerSkills(dto.getComputerSkills());
        volunteer.setCanva(dto.getCanva());
        volunteer.setInterests(dto.getInterests());
        volunteer.setInterestOther(dto.getInterestOther());
        volunteer.setExperience(dto.getExperience());
        volunteer.setExpectations(dto.getExpectations());
        volunteer.setInstagram(dto.getInstagram());
        volunteer.setFacebook(dto.getFacebook());
        volunteer.setLinkedin(dto.getLinkedin());
        volunteer.setExtraNotes(dto.getExtraNotes());

        if (dto.getStatus() != null) {
            volunteer.setStatus(dto.getStatus());
        }

        volunteer.setAdmissionDate(dto.getAdmissionDate());
        volunteer.setEndDate(dto.getEndDate());
        volunteer.setActive(dto.isActive());
        volunteer.setCertificateIssued(dto.isCertificateIssued());

        // Komanda təyinatı
        if (dto.getTeamId() != null) {
            Team team = teamRepository.findById(dto.getTeamId())
                    .orElseThrow(() -> new RuntimeException("Komanda tapılmadı: ID " + dto.getTeamId()));
            volunteer.setTeam(team);
        } else {
            volunteer.setTeam(null);
        }
    }

    private VolunteerResponseDTO mapToDto(Volunteer v) {
        VolunteerResponseDTO dto = new VolunteerResponseDTO();
        dto.setId(v.getId());
        dto.setFirstName(v.getFirstName());
        dto.setLastName(v.getLastName());
        dto.setFatherName(v.getFatherName());
        dto.setGender(v.getGender());
        dto.setBirthDate(v.getBirthDate());
        dto.setEmail(v.getEmail());
        dto.setPhone(v.getPhone());
        dto.setAddress(v.getAddress());
        dto.setCitizenship(v.getCitizenship());
        dto.setCitizenshipOther(v.getCitizenshipOther());
        dto.setEducation(v.getEducation());
        dto.setEducationOther(v.getEducationOther());
        dto.setInstitution(v.getInstitution());
        dto.setSpecialty(v.getSpecialty());
        dto.setLanguages(v.getLanguages());
        dto.setComputerSkills(v.getComputerSkills());
        dto.setCanva(v.getCanva());
        dto.setInterests(v.getInterests());
        dto.setInterestOther(v.getInterestOther());
        dto.setExperience(v.getExperience());
        dto.setExpectations(v.getExpectations());
        dto.setInstagram(v.getInstagram());
        dto.setFacebook(v.getFacebook());
        dto.setLinkedin(v.getLinkedin());
        dto.setExtraNotes(v.getExtraNotes());
        dto.setStatus(v.getStatus());
        dto.setAdmissionDate(v.getAdmissionDate());
        dto.setEndDate(v.getEndDate());
        dto.setActive(v.isActive());
        dto.setCertificateIssued(v.isCertificateIssued());

        if (v.getTeam() != null) {
            dto.setTeamId(v.getTeam().getId());
            dto.setTeamName(v.getTeam().getName());
        }

        return dto;
    }

    private VolunteerResponseDTO mapToResponseDTO(Volunteer v) {
        VolunteerResponseDTO dto = new VolunteerResponseDTO();
        dto.setId(v.getId());
        dto.setFirstName(v.getFirstName());
        dto.setLastName(v.getLastName());
        dto.setEmail(v.getEmail());
        dto.setPhone(v.getPhone());
        dto.setInstitution(v.getInstitution());
        dto.setSpecialty(v.getSpecialty());
        dto.setInterests(v.getInterests());
        dto.setBirthDate(v.getBirthDate());
        return dto;
    }

    // Statistika kartları üçün (Aktiv könüllülər və Ümumi komandalar)
    public VolunteerStatsDTO getVolunteerDashboardStats() {
        // Yalnız statusu "Qəbul edildi" və aktiv olan könüllüləri sayır
        long activeCount = volunteerRepository.countByStatusAndActiveTrue("Qəbul edildi");

        // Ümumi yaratdığınız komandaların sayını alır
        long teamsCount = teamRepository.count();

        return new VolunteerStatsDTO(activeCount, teamsCount);
    }

    // Sertifikat statistikası (Gözləyən və Verilən)
    public CertificateStatsDTO getCertificateStats() {
        long pending = volunteerRepository.countByCertificateIssuedFalse();
        long issued = volunteerRepository.countByCertificateIssuedTrue();
        return new CertificateStatsDTO(pending, issued);
    }

    @Override
    public List<VolunteerResponseDTO> getFilteredApplications(String search, String status) {
        List<Volunteer> volunteers = volunteerRepository.findAll();

        if (status != null && !status.isBlank() && !status.equalsIgnoreCase("Hamısı")) {
            volunteers = volunteers.stream()
                    .filter(v -> v.getStatus() != null && v.getStatus().equalsIgnoreCase(status))
                    .collect(Collectors.toList());
        }

        if (search != null && !search.isBlank()) {
            String query = search.toLowerCase();
            volunteers = volunteers.stream()
                    .filter(v -> (v.getFirstName() != null && v.getFirstName().toLowerCase().contains(query)) ||
                            (v.getLastName() != null && v.getLastName().toLowerCase().contains(query)) ||
                            (v.getEmail() != null && v.getEmail().toLowerCase().contains(query)))
                    .collect(Collectors.toList());
        }

        return volunteers.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public VolunteerResponseDTO issueCertificate(Long id) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Könüllü tapılmadı: " + id));

        volunteer.setCertificateIssued(true);
        Volunteer saved = volunteerRepository.save(volunteer);

        return mapToDto(saved);
    }
}
