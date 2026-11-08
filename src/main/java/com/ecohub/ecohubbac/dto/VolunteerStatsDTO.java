package com.ecohub.ecohubbac.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerStatsDTO {
    private long activeVolunteers; // Aktiv könüllülərin sayı
    private long totalTeams;       // Ümumi komandaların sayı
}
