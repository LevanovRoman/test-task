package com.myapp.testtask.service;

import com.myapp.testtask.dto.CustomerDto;
import com.myapp.testtask.entity.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer createCustomer(CustomerDto customerDto);

    Optional<Customer> getCustomerById(Long id) throws Exception;

    List<Customer> getAllCustomers();

    Customer updateCustomer(Long id, CustomerDto customerDto);

    void deleteCustomer(Long id);

    CustomerDto createCustomerWithPhoto(CustomerDto customerDto, MultipartFile file) throws IOException;

    CustomerDto getCustomerWithPhoto(Long id);

    List<CustomerDto> getAllCustomersWithPhoto();
}
