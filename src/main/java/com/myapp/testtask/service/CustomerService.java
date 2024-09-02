package com.myapp.testtask.service;

import com.myapp.testtask.dto.CustomerDto;
import com.myapp.testtask.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer createCustomer(CustomerDto customerDto);

    Optional<Customer> getCustomerById(Long id) throws Exception;

    List<Customer> getAllCustomers();

    Customer updateCustomer(Long id, CustomerDto customerDto);

    void deleteCustomer(Long id);

}
