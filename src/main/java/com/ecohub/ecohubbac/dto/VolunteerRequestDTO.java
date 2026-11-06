package com.ecohub.ecohubbac.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class VolunteerRequestDTO {
    private String firstName;
    private String lastName;
    private String fatherName;
    private String gender;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private String address;
    private String citizenship;
    private String citizenshipOther;
    private String education;
    private String educationOther;
    private String institution;
    private String specialty;

    private String languages;
    private String computerSkills;
    private String canva;
    private List<String> interests;
    private String interestOther;
    private String experience;
    private String expectations;
    private String instagram;
    private String facebook;
    private String linkedin;
    private String extraNotes;
}
