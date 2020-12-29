package com.orangetalents.orangetalentschallenge.mapper;

import com.orangetalents.orangetalentschallenge.model.domain.User;
import com.orangetalents.orangetalentschallenge.model.request.UserPostRequestBody;
import com.orangetalents.orangetalentschallenge.model.request.UserPostResponseBody;
import com.orangetalents.orangetalentschallenge.util.UserCreator;
import com.orangetalents.orangetalentschallenge.util.UserPostRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {UserMapperImpl.class, UserPostResponseBodyMapperImpl.class})
@DisplayName("Teste for User mapping")
class UserObjectsMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserPostResponseBodyMapper userPostResponseBodyMapper;

    @Test
    @DisplayName("Test method toUser then return User to be saved when receive valid UserPostRequestBody")
    void toUser_ReturnsUserToBeSaved_WhenReceiveValidUserPostRequestBody(){
        UserPostRequestBody userPostRequestBody = UserPostRequestBodyCreator.createValidUserPostRequestBody();

        User user = userMapper.toUser(userPostRequestBody);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getName()).isEqualTo(userPostRequestBody.getName());
        Assertions.assertThat(user.getEmail()).isEqualTo(userPostRequestBody.getEmail());
        Assertions.assertThat(user.getCpf()).isEqualTo(userPostRequestBody.getCpf());
        Assertions.assertThat(user.getBirthday()).isEqualTo(userPostRequestBody.getBirthday());
    }

    @Test
    @DisplayName("Test method toUserPostResponseBody then return UserPostResponseBody when receive valid User")
    void toUserPostResponseBody_ReturnsValidUserPostResponseBody_WhenReceiveValidUser(){
        User user = UserCreator.createValidUser();

        UserPostResponseBody userPostResponseBody = userPostResponseBodyMapper.toUserPostResponseBody(user);

        Assertions.assertThat(userPostResponseBody).isNotNull();
        Assertions.assertThat(userPostResponseBody.getId()).isEqualTo(user.getId());
    }
}
