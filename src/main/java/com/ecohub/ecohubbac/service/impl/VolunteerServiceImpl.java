package com.ecohub.ecohubbac.service.impl;

import com.ecohub.ecohubbac.dto.VolunteerRequestDTO;
import com.ecohub.ecohubbac.dto.VolunteerResponseDTO;
import com.ecohub.ecohubbac.entity.Volunteer;
import com.ecohub.ecohubbac.repository.VolunteerRepository;
import com.ecohub.ecohubbac.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerRepository volunteerRepository;

    @Override
    public void createVolunteerApplication(VolunteerRequestDTO requestDTO) {
        Volunteer volunteer = new Volunteer();
        volunteer.setFirstName(requestDTO.getFirstName());
        volunteer.setLastName(requestDTO.getLastName());
        volunteer.setFatherName(requestDTO.getFatherName());
        volunteer.setGender(requestDTO.getGender());
        volunteer.setBirthDate(requestDTO.getBirthDate());
        volunteer.setEmail(requestDTO.getEmail());
        volunteer.setPhone(requestDTO.getPhone());
        volunteer.setAddress(requestDTO.getAddress());
        volunteer.setCitizenship(requestDTO.getCitizenship());
        volunteer.setCitizenshipOther(requestDTO.getCitizenshipOther());
        volunteer.setEducation(requestDTO.getEducation());
        volunteer.setEducationOther(requestDTO.getEducationOther());
        volunteer.setInstitution(requestDTO.getInstitution());
        volunteer.setSpecialty(requestDTO.getSpecialty());

        volunteer.setLanguages(requestDTO.getLanguages());
        volunteer.setComputerSkills(requestDTO.getComputerSkills());
        volunteer.setCanva(requestDTO.getCanva());
        volunteer.setInterests(requestDTO.getInterests());
        volunteer.setInterestOther(requestDTO.getInterestOther());
        volunteer.setExperience(requestDTO.getExperience());
        volunteer.setExpectations(requestDTO.getExpectations());
        volunteer.setInstagram(requestDTO.getInstagram());
        volunteer.setFacebook(requestDTO.getFacebook());
        volunteer.setLinkedin(requestDTO.getLinkedin());
        volunteer.setExtraNotes(requestDTO.getExtraNotes());

        volunteerRepository.save(volunteer);
    }

    @Override
    public List<VolunteerResponseDTO> getAllVolunteers() {
        return volunteerRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VolunteerResponseDTO getVolunteerById(Long id) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Könüllü müraciəti tapılmadı: ID " + id));
        return mapToResponseDTO(volunteer);
    }

    @Override
    public void deleteVolunteer(Long id) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Könüllü müraciəti tapılmadı: ID " + id));
        volunteerRepository.delete(volunteer);
    }

    @Override
    public void updateStatus(Long id, String status) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Müraciət tapılmadı. ID: " + id));

        volunteer.setStatus(status);
        volunteerRepository.save(volunteer);
    }

    private VolunteerResponseDTO mapToResponseDTO(Volunteer v) {
        VolunteerResponseDTO dto = new VolunteerResponseDTO();
        dto.setId(v.getId());
        dto.setFirstName(v.getFirstName());
        dto.setLastName(v.getLastName());
        dto.setEmail(v.getEmail());
        dto.setPhone(v.getPhone());
        dto.setInstitution(v.getInstitution());
        dto.setSpecialty(v.getSpecialty());
        dto.setInterests(v.getInterests());
        dto.setBirthDate(v.getBirthDate());
        return dto;
    }
}
