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
    private String title;
    private String subtitle;
    private String description;
    private String content;
    private String date;
    private String image;
}