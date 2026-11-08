package com.ecohub.ecohubbac.dto;
import lombok.Data;
import java.util.List;

@Data
public class TeamResponseDTO {
    private Long id;
    private String name;
    private int volunteerCount; // Komandadakı könüllülərin sayı
    private List<VolunteerResponseDTO> volunteers; // Həmin komandadakı könüllülərin siyahısı (istəyə bağlı)
}