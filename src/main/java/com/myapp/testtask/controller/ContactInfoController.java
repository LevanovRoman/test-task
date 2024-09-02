package com.myapp.testtask.controller;

import com.myapp.testtask.dto.ContactInfoDto;
import com.myapp.testtask.dto.CustomerDto;
import com.myapp.testtask.entity.Customer;
import com.myapp.testtask.service.ContactInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-info")
@RequiredArgsConstructor
public class ContactInfoController {

    private final ContactInfoService contactInfoService;

    @GetMapping
    public ResponseEntity<List<ContactInfoDto>> getAllContacts(){
        return ResponseEntity.ok(contactInfoService.getAllContactInfo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactInfoDto> getContactById(@PathVariable Long id){
        return ResponseEntity.ok(contactInfoService.getContactInfoById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createContactInfo(@RequestBody ContactInfoDto contactInfoDto){
        return ResponseEntity.ok(contactInfoService.createContactInfo(contactInfoDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ContactInfoDto> updateContactById(@PathVariable Long id,
                                                             @RequestBody ContactInfoDto contactInfoDto){
        return ResponseEntity.ok(contactInfoService.updateContactInfo(id, contactInfoDto));
    }
}
