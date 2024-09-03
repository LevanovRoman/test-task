package com.myapp.testtask.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Поле с фамилией должно быть заполнено!")
    private String lastName;

    @NotBlank(message = "Поле с именем должно быть заполнено!")
    private String firstName;

    private String patronymic;

    private String email;

    private LocalDate birthDate;

    @NotBlank(message = "Поле с номером телефона должно быть заполнено!")
    private String phoneNumber;

    private String photo;
}
