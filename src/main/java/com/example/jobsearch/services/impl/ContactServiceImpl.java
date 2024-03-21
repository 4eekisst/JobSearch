package com.example.jobsearch.services.impl;

import com.example.jobsearch.dao.ContactDao;
import com.example.jobsearch.dto.ContactDto;
import com.example.jobsearch.exceptions.ContactNotFoundException;
import com.example.jobsearch.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactDao contactDao;

    @Override
    public void createContact(ContactDto contactDto) {
        contactDao.createContact(contactDto);
    }

    @Override
    public void deleteContactById(Long id) throws ContactNotFoundException {
        contactDao.deleteContactById(id);
    }

    @Override
    public void editContact(ContactDto contactDto) throws ContactNotFoundException {
        contactDao.editContact(contactDto);
    }

    @Override
    public ContactDto getContactById(Long id) throws ContactNotFoundException {
        return contactDao.getContactById(id);
    }
}
