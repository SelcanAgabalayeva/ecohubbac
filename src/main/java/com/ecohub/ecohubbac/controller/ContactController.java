package com.ecohub.ecohubbac.controller;

import com.ecohub.ecohubbac.dto.ContactRequestDTO;
import com.ecohub.ecohubbac.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;
    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody ContactRequestDTO requestDTO) {
        contactService.sendMessage(requestDTO);
        return ResponseEntity.ok("Mesajınız uğurla göndərildi!");
    }
}
