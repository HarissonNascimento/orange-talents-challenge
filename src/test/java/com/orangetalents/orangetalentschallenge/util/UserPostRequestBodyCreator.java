package com.orangetalents.orangetalentschallenge.util;

import com.orangetalents.orangetalentschallenge.model.request.UserPostRequestBody;

import java.time.LocalDate;

public class UserPostRequestBodyCreator {
    public static UserPostRequestBody createValidUserPostRequestBody(){
        return UserPostRequestBody.builder()
                .name("name")
                .email("email@test.com")
                .cpf("11111111111")
                .birthday(LocalDate.ofYearDay(1999, 20))
                .build();
    }

    public static UserPostRequestBody createInvalidUserPostRequestBody(){
        return UserPostRequestBody.builder()
                .name("")
                .email("")
                .cpf("")
                .birthday(LocalDate.ofYearDay(1999, 20))
                .build();
    }

}
