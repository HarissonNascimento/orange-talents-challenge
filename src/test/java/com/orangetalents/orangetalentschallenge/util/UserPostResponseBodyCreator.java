package com.orangetalents.orangetalentschallenge.util;

import com.orangetalents.orangetalentschallenge.model.request.UserPostResponseBody;

public class UserPostResponseBodyCreator {
    public static UserPostResponseBody createValidUserPostResponseBody(){
        return UserPostResponseBody.builder()
                .id(1L)
                .build();
    }
}
