package com.orangetalents.orangetalentschallenge.endpoint.controller;

import com.orangetalents.orangetalentschallenge.endpoint.service.UserService;
import com.orangetalents.orangetalentschallenge.model.request.UserPostRequestBody;
import com.orangetalents.orangetalentschallenge.model.request.UserPostResponseBody;
import com.orangetalents.orangetalentschallenge.util.UserCreator;
import com.orangetalents.orangetalentschallenge.util.UserPostRequestBodyCreator;
import com.orangetalents.orangetalentschallenge.util.UserPostResponseBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for UserController")
class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userServiceMock;

    @BeforeEach
    public void setUp(){
        BDDMockito.when(userServiceMock.createNewUser(any()))
                .thenReturn(UserPostResponseBodyCreator.createValidUserPostResponseBody());
    }

    @Test
    @DisplayName("Create new User form UserPostRequestBody the return UserPostResponseBody when successful")
    void createNewUser_ReturnUserPostResponseBody_WhenSuccessful(){
        UserPostRequestBody validUserPostRequestBody = UserPostRequestBodyCreator.createValidUserPostRequestBody();

        UserPostResponseBody userPostResponseBody = userController.createNewUser(validUserPostRequestBody).getBody();

        Assertions.assertThat(userPostResponseBody).isNotNull();
        Assertions.assertThat(userPostResponseBody.getId()).isEqualTo(UserCreator.createValidUser().getId());
    }
}
