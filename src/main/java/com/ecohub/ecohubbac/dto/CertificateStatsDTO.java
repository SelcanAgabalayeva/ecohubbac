package com.ecohub.ecohubbac.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateStatsDTO {
    private long pendingCertificates; // Sertifikat gözləyənlər (certificateIssued = false)
    private long issuedCertificates;  // Sertifikat verilənlər (certificateIssued = true)
}
