package com.ecohub.ecohubbac.controller.dashboard;

import com.ecohub.ecohubbac.dto.DashboardStatsDTO;
import com.ecohub.ecohubbac.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/dashboard")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsDTO> getDashboardStats() {

        return ResponseEntity.ok(dashboardService.getDashboardStats());
    }

}
