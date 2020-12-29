package com.orangetalents.orangetalentschallenge.util;

import com.orangetalents.orangetalentschallenge.model.domain.User;

import java.time.LocalDate;

public class UserCreator {
    public static User createValidUser(){
        return User.builder()
                .id(1L)
                .name("name")
                .email("email@test.com")
                .cpf("11111111111")
                .birthday(LocalDate.ofYearDay(1999, 20))
                .build();
    }

    public static User createValidUserToBeSaved(){
        return User.builder()
                .name("name")
                .email("email@test.com")
                .cpf("11111111111")
                .birthday(LocalDate.ofYearDay(1999, 20))
                .build();
    }
}
