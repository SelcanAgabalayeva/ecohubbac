package com.ecohub.ecohubbac.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class VolunteerResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String institution;
    private String specialty;
    private List<String> interests;
    private LocalDate birthDate;
}