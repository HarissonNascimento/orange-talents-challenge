package com.orangetalents.orangetalentschallenge.endpoint.service;

import com.orangetalents.orangetalentschallenge.endpoint.repository.UserRepository;
import com.orangetalents.orangetalentschallenge.mapper.UserMapper;
import com.orangetalents.orangetalentschallenge.mapper.UserPostResponseBodyMapper;
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
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for UserService")
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private UserMapper userMapperMock;

    @Mock
    private UserPostResponseBodyMapper userPostResponseBodyMapperMock;

    @BeforeEach
    public void setUp(){
        BDDMockito.when(userPostResponseBodyMapperMock.toUserPostResponseBody(any()))
                .thenReturn(UserPostResponseBodyCreator.createValidUserPostResponseBody());

        BDDMockito.when(userMapperMock.toUser(any()))
                .thenReturn(UserCreator.createValidUserToBeSaved());
    }

    @Test
    @DisplayName("Create new User from UserPostRequestBody the return UserPostResponseBody when successful")
    void createNewUser_ReturnUserPostResponseBody_WhenSuccessful(){
        UserPostRequestBody validUserPostRequestBody = UserPostRequestBodyCreator.createValidUserPostRequestBody();

        UserPostResponseBody newUser = userService.createNewUser(validUserPostRequestBody);

        Assertions.assertThat(newUser).isNotNull();
        Assertions.assertThat(newUser.getId()).isEqualTo(UserCreator.createValidUser().getId());
    }

    @Test
    @DisplayName("Check exists User by email return true if email already exists")
    void existsUserByEmail_ReturnTrue_IfEmailAlreadyExists(){
        BDDMockito.when(userRepositoryMock.existsUserByEmail(anyString()))
                .thenReturn(Boolean.TRUE);

        Boolean result = userService.existsUserByEmail("email@test.com");

        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Check exists User by email return false if email does not exists")
    void existsUserByEmail_ReturnFalse_IfEmailDoesNotExists(){
        BDDMockito.when(userRepositoryMock.existsUserByEmail(anyString()))
                .thenReturn(Boolean.FALSE);

        Boolean result = userService.existsUserByEmail("email@test.com");

        Assertions.assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Check exists User by CPF return true if CPF already taken")
    void existsUserByCpf_ReturnTrue_IfCPFAlreadyTaken(){
        BDDMockito.when(userRepositoryMock.existsUserByCpf(anyString()))
                .thenReturn(Boolean.TRUE);

        Boolean result = userService.existsUserByCpf("11111111111");

        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Check exists User by CPF return false if CPF does not taken")
    void existsUserByCpf_ReturnFalse_IfCPFDoesNotTaken(){
        BDDMockito.when(userRepositoryMock.existsUserByCpf(anyString()))
                .thenReturn(Boolean.FALSE);

        Boolean result = userService.existsUserByCpf("11111111111");

        Assertions.assertThat(result).isFalse();
    }
}
