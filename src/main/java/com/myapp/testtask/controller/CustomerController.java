package com.myapp.testtask.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.myapp.testtask.dto.CustomerDto;
import com.myapp.testtask.entity.Customer;
import com.myapp.testtask.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "methods for customers")
@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Просмотр всех пользователей")
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @Operation(summary = "Получение пользователя")
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) throws Exception{
        return customerService.getCustomerById(id).map(ResponseEntity :: ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Создание пользователя")
    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CustomerDto customerDto){
        return ResponseEntity.ok(customerService.createCustomer(customerDto));
    }

    @Operation(summary = "Редактирование пользователя")
    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomerById(@PathVariable Long id, @RequestBody CustomerDto customerDto){
        return ResponseEntity.ok(customerService.updateCustomer(id, customerDto));
    }

    @Operation(summary = "Удаление пользователя")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Success");
    }

    @Operation(summary = "Создание пользователя с фото")
    @PostMapping("/create-with-photo")
    public ResponseEntity<CustomerDto> createCustomerWithPhoto(@RequestPart MultipartFile file,
                                                               @RequestPart String customerDto) throws IOException {
        CustomerDto dto = convertToCustomerDto(customerDto);
        return ResponseEntity.ok(customerService.createCustomerWithPhoto(dto, file));
    }

    @Operation(summary = "Получение пользователя с фото")
    @GetMapping("/with-photo/{id}")
    public ResponseEntity<CustomerDto> getCustomerWithPhotoById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerWithPhoto(id));
    }

    @Operation(summary = "Просмотр всех пользователей с фото")
    @GetMapping("/with-photo")
    public ResponseEntity<List<CustomerDto>> getAllCustomersWithPhoto() {
        return ResponseEntity.ok(customerService.getAllCustomersWithPhoto());
    }

    private CustomerDto convertToCustomerDto(String customerDtoObj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(customerDtoObj, CustomerDto.class);
    }

}
