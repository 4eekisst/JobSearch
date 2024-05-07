package com.example.jobsearch.services;

import com.example.jobsearch.dto.ContactType.ContactTypeDto;
import com.example.jobsearch.dto.ContactType.ContactsTypeWithIdDto;
import com.example.jobsearch.models.ContactType;

public interface ContactsTypeService {
    ContactsTypeWithIdDto getContactTypeByName(String contactsType);

    Long createNewContacts(String type);

    ContactsTypeWithIdDto getContactTypeById(Long typeId);

    ContactType getTypeWithId(Long contactTypeId);
}
