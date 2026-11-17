package com.ecohub.ecohubbac.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "volunteers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String fatherName;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    private LocalDate createdAt;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(nullable = false)
    private String citizenship;

    private String citizenshipOther;

    @Column(nullable = false)
    private String education;

    private String educationOther;

    @Column(nullable = false)
    private String institution;

    @Column(nullable = false)
    private String specialty;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String languages;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String computerSkills;

    @Column(nullable = false)
    private String canva;

    @ElementCollection
    @CollectionTable(
            name = "volunteer_interests",
            joinColumns = @JoinColumn(name = "volunteer_id")
    )
    @Column(name = "interest")
    private List<String> interests;

    private String interestOther;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String experience;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String expectations;

    private String instagram;
    private String facebook;
    private String linkedin;

    @Column(columnDefinition = "TEXT")
    private String extraNotes;

    private String status = "Yeni";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team team;

    private String admissionDate;
    private String endDate;

    private boolean active = true;
    private boolean certificateIssued = false;

    @Column(name = "team_leader", nullable = false)
    private boolean teamLeader = false;
}