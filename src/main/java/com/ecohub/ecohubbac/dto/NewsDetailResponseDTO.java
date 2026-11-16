package com.ecohub.ecohubbac.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDetailResponseDTO {
    private Long id;
    private String category;

    private String titleAz;
    private String titleEn;

    private String subtitleAz;
    private String subtitleEn;

    private String descriptionAz;
    private String descriptionEn;

    private String contentAz;
    private String contentEn;

    private String date;
    private String image;
}