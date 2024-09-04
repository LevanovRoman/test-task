package com.myapp.testtask.service;

import com.myapp.testtask.dto.CustomerDto;
import com.myapp.testtask.entity.Customer;
import com.myapp.testtask.exception.CustomerNotFoundException;
import com.myapp.testtask.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final FileService fileService;

    @Value("${project.photo}")
    private String path;

    @Value("${base.url}")
    private String baseUrl;

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

    @Override
    public CustomerDto createCustomerWithPhoto(CustomerDto customerDto, MultipartFile file) throws IOException {
        // 1. скачиваем файл
        String uploadedFileName = fileService.uploadFile(path, file);
        // 2. устанавливаем значение 'photo'
        customerDto.setPhoto(uploadedFileName);
        // 3. CustomerDto в объект Customer
        Customer customer = new Customer(
                null,
                customerDto.getLastName(),
                customerDto.getFirstName(),
                customerDto.getPatronymic(),
                customerDto.getEmail(),
                customerDto.getBirthDate(),
                customerDto.getPhoneNumber(),
                customerDto.getPhoto()
        );
        // сохраняем объект
        Customer savedCustomer = customerRepository.save(customer);
        // генерируем адрес
        String photoUrl = baseUrl + "/file/" + uploadedFileName;
        // переводим в DTO и возвращаем
        return new CustomerDto(
                savedCustomer.getId(),
                savedCustomer.getLastName(),
                savedCustomer.getFirstName(),
                savedCustomer.getPatronymic(),
                savedCustomer.getEmail(),
                savedCustomer.getBirthDate(),
                savedCustomer.getPhoneNumber(),
                savedCustomer.getPhoto(),
                photoUrl
        );
    }

    @Override
    public CustomerDto getCustomerWithPhoto(Long id) {
        // проверяем наличие пользователя в базе данных
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Пользователь с ID " + id + " не существует."));
        // генерируем адрес
        String photoUrl = baseUrl + "/file/" + customer.getPhoto();
        // переводим в DTO и возвращаем
        return new CustomerDto(
                customer.getId(),
                customer.getLastName(),
                customer.getFirstName(),
                customer.getPatronymic(),
                customer.getEmail(),
                customer.getBirthDate(),
                customer.getPhoneNumber(),
                customer.getPhoto(),
                photoUrl
        );
    }

    @Override
    public List<CustomerDto> getAllCustomersWithPhoto() {
        List<Customer> customers = customerRepository.findAll();

        List<CustomerDto> customerDtos = new ArrayList<>();

        for(Customer customer : customers) {
            String photoUrl = baseUrl + "/file/" + customer.getPhoto();
            CustomerDto customerDto = new CustomerDto(
                    customer.getId(),
                    customer.getLastName(),
                    customer.getFirstName(),
                    customer.getPatronymic(),
                    customer.getEmail(),
                    customer.getBirthDate(),
                    customer.getPhoneNumber(),
                    customer.getPhoto(),
                    photoUrl
            );
            customerDtos.add(customerDto);
        }

        return customerDtos;
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
