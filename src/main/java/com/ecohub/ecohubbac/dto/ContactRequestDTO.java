package com.ecohub.ecohubbac.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequestDTO {
    private String fullName;
    private String email;
    private String subject;
    private String message;
}
