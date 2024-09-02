package com.myapp.testtask.dto;

import java.time.LocalDate;

public record CustomerDto(
        String lastName,
        String firstName,
        String patronymic,
        String email,
        LocalDate birthDate,
        String phoneNumber,
        String photo
) {

}
