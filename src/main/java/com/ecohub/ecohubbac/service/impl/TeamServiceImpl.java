package com.ecohub.ecohubbac.service.impl;

import com.ecohub.ecohubbac.dto.TeamRequestDTO;
import com.ecohub.ecohubbac.dto.TeamResponseDTO;
import com.ecohub.ecohubbac.dto.VolunteerResponseDTO;
import com.ecohub.ecohubbac.entity.Team;
import com.ecohub.ecohubbac.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl {

    private final TeamRepository teamRepository;

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
    private TeamResponseDTO mapToDto(Team team) {
        TeamResponseDTO dto = new TeamResponseDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());

        if (team.getVolunteers() != null) {
            dto.setVolunteerCount(team.getVolunteers().size());

            // İstəsəniz könüllülərin də qısa məlumatlarını əlavə edə bilərsiniz
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
}
