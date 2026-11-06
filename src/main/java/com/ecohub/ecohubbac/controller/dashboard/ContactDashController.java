package com.ecohub.ecohubbac.controller.dashboard;

import com.ecohub.ecohubbac.dto.ContactRequestDTO;
import com.ecohub.ecohubbac.entity.ContactMessage;
import com.ecohub.ecohubbac.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/contact")
@RequiredArgsConstructor
public class ContactDashController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<String> sendadminMessage(@RequestBody ContactRequestDTO requestDTO) {
        contactService.sendMessage(requestDTO);
        return ResponseEntity.ok("Mesajınız uğurla göndərildi!");
    }
    @GetMapping
    public ResponseEntity<List<ContactMessage>> getAllMessages() {
        return ResponseEntity.ok(contactService.getAllMessages());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long id) {
        contactService.deleteMessage(id);
        return ResponseEntity.ok("Mesaj uğurla silindi. ID: " + id);
    }
}
