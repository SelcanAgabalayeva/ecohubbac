package com.ecohub.ecohubbac.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardStatsDTO {
    private long totalVolunteers;      // Ümumi müraciətlər (128)
    private long newVolunteers;        // Yeni müraciətlər / baxılmağı gözləyən (24)
    private long acceptedVolunteers;   // Qəbul edilənlər / təsdiqlənənlər (76)
    private long totalProjects;        // Layihələr (12)
}
