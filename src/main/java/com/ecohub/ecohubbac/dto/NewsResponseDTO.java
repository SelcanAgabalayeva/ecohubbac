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

    private String titleAz;
    private String titleEn;

    private String descriptionAz;
    private String descriptionEn;

    private String date;
    private String image;
}
