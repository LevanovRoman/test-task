package com.myapp.testtask.service;

import com.myapp.testtask.dto.CustomerDto;
import com.myapp.testtask.entity.Customer;
import com.myapp.testtask.exception.CustomerNotFoundException;
import com.myapp.testtask.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        saveCustomer(customer, customerDto);
        return customer;
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return Optional.ofNullable(customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Пользователь с ID " + id + " не существует.")));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Пользователь с ID " + id + " не существует."));
        saveCustomer(customer, customerDto);
        return customer;
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Пользователь с ID " + id + " не существует."));
        customerRepository.deleteById(id);
    }

    public void saveCustomer(Customer customer, CustomerDto customerDto){
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPatronymic(customerDto.getPatronymic());
        customer.setBirthDate(customerDto.getBirthDate());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setPhoto(customerDto.getPhoto());
        customerRepository.save(customer);
    }
}
