package com.myapp.testtask.service;

import com.myapp.testtask.dto.ContactInfoDto;
import com.myapp.testtask.entity.Customer;

import java.util.List;

public interface ContactInfoService {

    Customer createContactInfo(ContactInfoDto contactInfoDto);

    ContactInfoDto getContactInfoById(Long id);

    List<ContactInfoDto> getAllContactInfo();

    ContactInfoDto updateContactInfo(Long id, ContactInfoDto contactInfoDto);

    void deleteContactInfo(Long id);


}
