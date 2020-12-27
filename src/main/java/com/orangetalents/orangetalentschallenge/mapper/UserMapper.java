package com.orangetalents.orangetalentschallenge.mapper;

import com.orangetalents.orangetalentschallenge.model.domain.User;
import com.orangetalents.orangetalentschallenge.model.request.UserPostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toUser(UserPostRequestBody userPostRequestBody);
}
