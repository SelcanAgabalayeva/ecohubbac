package com.ecohub.ecohubbac.service;


import com.ecohub.ecohubbac.dto.ContactRequestDTO;
import com.ecohub.ecohubbac.entity.ContactMessage;

import java.util.List;

public interface ContactService {
    ContactMessage sendMessage(ContactRequestDTO requestDTO);
    List<ContactMessage> getAllMessages();
    void deleteMessage(Long id);


}
