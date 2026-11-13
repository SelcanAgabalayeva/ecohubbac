package com.ecohub.ecohubbac.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetailResponseDTO {
    private Long id;
    private String category;
    private String title;
    private String subtitle;
    private String image;
    private String description;
    private String mission;
    private List<String> activities;
}
