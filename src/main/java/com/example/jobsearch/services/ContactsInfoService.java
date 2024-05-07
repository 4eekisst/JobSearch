package com.example.jobsearch.services;

import com.example.jobsearch.dto.ContactInfo.ContactsInfoDto;
import com.example.jobsearch.dto.ContactInfo.ContactsInfoWithIdDto;
import com.example.jobsearch.models.Resume;

import java.util.List;

public interface ContactsInfoService {

    void createNewContactsInfo(ContactsInfoDto contactsInfo, Resume r);
    List<ContactsInfoWithIdDto> getContactsByResumeId(Resume resume);


}
