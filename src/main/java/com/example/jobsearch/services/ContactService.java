package com.example.jobsearch.services;

import com.example.jobsearch.dto.ContactDto;
import com.example.jobsearch.exceptions.ContactNotFoundException;

public interface ContactService {
    void createContact(ContactDto contactDto);
    void deleteContactById(Long id) throws ContactNotFoundException;
    void editContact(ContactDto contactDto) throws ContactNotFoundException;
    ContactDto getContactById(Long id) throws ContactNotFoundException;
}
