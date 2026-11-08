package com.ecohub.ecohubbac.repository;

import com.ecohub.ecohubbac.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    long countByStatus(String status);
    List<Volunteer> findByTeamId(Long teamId);
    List<Volunteer> findByStatus(String status);
    long countByStatusAndActiveTrue(String status);

    // Sertifikatı hələ verilməyənlərin (gözləyənlərin) sayı
    long countByCertificateIssuedFalse();

    // Sertifikatı verilənlərin sayı
    long countByCertificateIssuedTrue();
}
