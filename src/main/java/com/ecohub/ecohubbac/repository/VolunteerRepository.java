package com.ecohub.ecohubbac.repository;

import com.ecohub.ecohubbac.dto.CertificateStatsDTO;
import com.ecohub.ecohubbac.dto.VolunteerResponseDTO;
import com.ecohub.ecohubbac.dto.VolunteerStatsDTO;
import com.ecohub.ecohubbac.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    List<Volunteer> findByStatus(String status);

    long countByStatus(String status);

    List<Volunteer> findByTeamId(Long teamId);

    long countByStatusAndActiveTrue(String status);

    long countByCertificateIssuedFalse();

    long countByCertificateIssuedTrue();

    List<Volunteer> findTop5ByOrderByIdDesc();

}


