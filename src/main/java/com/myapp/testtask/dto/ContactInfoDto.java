package com.myapp.testtask.dto;

public record ContactInfoDto(
        String lastName,
        String firstName,
        String patronymic,
        String email,
        String phoneNumber
) {
}
