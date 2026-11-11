package com.ecohub.ecohubbac.service.impl;

import com.ecohub.ecohubbac.dto.TeamRequestDTO;
import com.ecohub.ecohubbac.dto.TeamResponseDTO;
import com.ecohub.ecohubbac.dto.VolunteerResponseDTO;
import com.ecohub.ecohubbac.entity.Team;
import com.ecohub.ecohubbac.entity.Volunteer;
import com.ecohub.ecohubbac.repository.TeamRepository;
import com.ecohub.ecohubbac.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl {

    private final TeamRepository teamRepository;
    private final VolunteerRepository volunteerRepository;

    // Bütün komandaları və onların tərkibini gətir
    public List<TeamResponseDTO> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ID-yə görə tək komanda
    public TeamResponseDTO getTeamById(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Komanda tapılmadı: ID " + id));
        return mapToDto(team);
    }

    // Yeni komanda yarat
    public TeamResponseDTO createTeam(TeamRequestDTO requestDTO) {
        Team team = new Team();
        team.setName(requestDTO.getName());
        Team savedTeam = teamRepository.save(team);
        return mapToDto(savedTeam);
    }

    // Komandanı sil
    public void deleteTeam(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Komanda tapılmadı: ID " + id));
        teamRepository.delete(team);
    }

    // ---------------- HELPER MAPPER ----------------
    // ---------------- HELPER MAPPER ----------------
    private TeamResponseDTO mapToDto(Team team) {
        TeamResponseDTO dto = new TeamResponseDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());

        // 🟢 Lider məlumatlarını DTO-ya ötürürük (Əgər lider təyin olunubsa)
        if (team.getLeader() != null) {
            dto.setLeaderId(team.getLeader().getId());
            dto.setLeaderFullName(team.getLeader().getFirstName() + " " + team.getLeader().getLastName());
        }

        if (team.getVolunteers() != null) {
            dto.setVolunteerCount(team.getVolunteers().size());

            List<VolunteerResponseDTO> volDtos = team.getVolunteers().stream().map(v -> {
                VolunteerResponseDTO vDto = new VolunteerResponseDTO();
                vDto.setId(v.getId());
                vDto.setFirstName(v.getFirstName());
                vDto.setLastName(v.getLastName());
                vDto.setEmail(v.getEmail());
                vDto.setStatus(v.getStatus());
                return vDto;
            }).collect(Collectors.toList());

            dto.setVolunteers(volDtos);
        } else {
            dto.setVolunteerCount(0);
        }

        return dto;
    }

    public TeamResponseDTO updateTeamLeader(Long teamId, Long volunteerId) {
        // 1. Komandanı tapırıq
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Komanda tapılmadı: ID " + teamId));

        // 2. Könüllünü tapırıq
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new RuntimeException("Könüllü tapılmadı: ID " + volunteerId));

        // 3. Lideri təyin edib yadda saxlayırıq
        team.setLeader(volunteer);
        Team savedTeam = teamRepository.save(team);

        return mapToDto(savedTeam);
    }
}
