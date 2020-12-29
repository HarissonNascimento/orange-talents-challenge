package com.orangetalents.orangetalentschallenge.mapper;

import com.orangetalents.orangetalentschallenge.model.domain.User;
import com.orangetalents.orangetalentschallenge.model.request.UserPostResponseBody;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPostResponseBodyMapper {
    UserPostResponseBody toUserPostResponseBody(User user);
}
