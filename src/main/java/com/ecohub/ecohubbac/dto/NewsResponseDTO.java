package com.ecohub.ecohubbac.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponseDTO {
    private Long id;
    private String category;
    private String title;
    private String description;
    private String date;
    private String image;
}
