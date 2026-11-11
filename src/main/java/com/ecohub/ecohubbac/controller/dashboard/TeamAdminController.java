package com.ecohub.ecohubbac.controller.dashboard;

import com.ecohub.ecohubbac.dto.TeamRequestDTO;
import com.ecohub.ecohubbac.dto.TeamResponseDTO;
import com.ecohub.ecohubbac.service.impl.TeamServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/teams")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TeamAdminController {

    private final TeamServiceImpl teamService;

    @GetMapping
    public ResponseEntity<List<TeamResponseDTO>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }


    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDTO> getTeamById(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getTeamById(id));
    }


    @PostMapping
    public ResponseEntity<TeamResponseDTO> createTeam(@RequestBody TeamRequestDTO requestDTO) {
        return ResponseEntity.ok(teamService.createTeam(requestDTO));
    }


    @PutMapping("/{teamId}/leader/{volunteerId}")
    public ResponseEntity<TeamResponseDTO> updateTeamLeader(
            @PathVariable Long teamId,
            @PathVariable Long volunteerId) {
        return ResponseEntity.ok(teamService.updateTeamLeader(teamId, volunteerId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.ok("Komanda uğurla silindi. ID: " + id);
    }
}
