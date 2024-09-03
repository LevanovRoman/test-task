package com.myapp.testtask.service;

import com.myapp.testtask.dto.ContactInfoDto;
import com.myapp.testtask.dto.CustomerDto;
import com.myapp.testtask.entity.Customer;
import com.myapp.testtask.exception.CustomerNotFoundException;
import com.myapp.testtask.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactInfoServiceImpl implements ContactInfoService{

    private final CustomerRepository customerRepository;

    @Override
    public Customer createContactInfo(ContactInfoDto contactInfoDto) {
        Customer customer = new Customer();
        saveCustomer(customer, contactInfoDto);
        return customer;
    }

    @Override
    public ContactInfoDto getContactInfoById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Пользователь с ID " + id + " не существует."));
        return mapCustomerToContactInfo(customer);
    }

    @Override
    public List<ContactInfoDto> getAllContactInfo() {
        List<ContactInfoDto> contactInfoDtos = customerRepository.findAll()
                .stream().map(this::mapCustomerToContactInfo).toList();
        return contactInfoDtos;
    }

    @Override
    public ContactInfoDto updateContactInfo(Long id, ContactInfoDto contactInfoDto) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Пользователь с ID " + id + " не существует."));
        saveCustomer(customer, contactInfoDto);
        return mapCustomerToContactInfo(customer);
    }

    @Override
    public void deleteContactInfo(Long id) {

    }

    public void saveCustomer(Customer customer, ContactInfoDto contactInfoDto){
        customer.setFirstName(contactInfoDto.firstName());
        customer.setLastName(contactInfoDto.lastName());
        customer.setEmail(contactInfoDto.email());
        customer.setPatronymic(contactInfoDto.patronymic());
        customer.setPhoneNumber(contactInfoDto.phoneNumber());
        customerRepository.save(customer);
    }

    public ContactInfoDto mapCustomerToContactInfo(Customer customer){
        return new ContactInfoDto(customer.getLastName(),
                customer.getFirstName(), customer.getPatronymic(), customer.getEmail(),
                customer.getPhoneNumber());
    }
}
