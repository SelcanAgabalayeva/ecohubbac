package com.ecohub.ecohubbac.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerRequestDTO {
    private String name;
    private String logoUrl;
    private String websiteUrl;
}
