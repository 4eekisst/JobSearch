package com.example.jobsearch.services.impl;

import com.example.jobsearch.dto.ContactInfo.ContactsInfoDto;
import com.example.jobsearch.dto.ContactInfo.ContactsInfoWithIdDto;
import com.example.jobsearch.dto.ContactType.ContactsTypeWithIdDto;
import com.example.jobsearch.models.ContactsInfo;
import com.example.jobsearch.models.Resume;
import com.example.jobsearch.repositories.ContactInfoRepository;
import com.example.jobsearch.services.ContactsInfoService;
import com.example.jobsearch.services.ContactsTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactsInfoServiceImpl implements ContactsInfoService {
    private final ContactInfoRepository contactInfoRepository;
    private final ContactsTypeService contactsTypeService;

    @Override
    public void createNewContactsInfo(ContactsInfoDto contactsInfo, Resume resume) {
        Long contactTypeId;
        if (contactsTypeService.getContactTypeByName(contactsInfo.getType()) == null) {
            contactTypeId = contactsTypeService.createNewContacts(contactsInfo.getType());
        } else {
            ContactsTypeWithIdDto type = contactsTypeService.getContactTypeByName(contactsInfo.getType());
            contactTypeId = type.getId();
        }
        ContactsInfo contactsInfo1 = ContactsInfo.builder()
                .type(contactsTypeService.getTypeWithId(contactTypeId))
                .resume(resume)
                .infoValue(contactsInfo.getInfoValue())
                .build();
        contactInfoRepository.save(contactsInfo1);
    }

    @Override
    public List<ContactsInfoWithIdDto> getContactsByResumeId(Resume resume) {
        List<ContactsInfo> contacts;
        List<ContactsInfoWithIdDto> contactsList = new ArrayList<>();
        contacts = contactInfoRepository.findByResume(resume);

        contacts.forEach(e -> contactsList.add(ContactsInfoWithIdDto.builder()
                .id(e.getResume().getId())
                .infoValue(e.getInfoValue())
                .type(e.getType().getType())
                .build()));
        return contactsList;
    }
}
