package com.ecohub.ecohubbac.service.impl;

import com.ecohub.ecohubbac.dto.ContactRequestDTO;
import com.ecohub.ecohubbac.entity.ContactMessage;
import com.ecohub.ecohubbac.repository.ContactRepository;
import com.ecohub.ecohubbac.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public ContactMessage sendMessage(ContactRequestDTO requestDTO) {
        ContactMessage contactMessage = new ContactMessage();
        contactMessage.setFullName(requestDTO.getFullName());
        contactMessage.setEmail(requestDTO.getEmail());
        contactMessage.setSubject(requestDTO.getSubject());
        contactMessage.setMessage(requestDTO.getMessage());

        return contactRepository.save(contactMessage);
    }

    @Override
    public List<ContactMessage> getAllMessages() {
        return contactRepository.findAll();
    }

    @Override
    public void deleteMessage(Long id) {
        ContactMessage message = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesaj tapılmadı: ID " + id));
        contactRepository.delete(message);
    }
}
