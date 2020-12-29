package com.orangetalents.orangetalentschallenge.integration;

import com.orangetalents.orangetalentschallenge.endpoint.repository.UserRepository;
import com.orangetalents.orangetalentschallenge.exception.ExceptionDetails;
import com.orangetalents.orangetalentschallenge.model.domain.User;
import com.orangetalents.orangetalentschallenge.model.request.UserPostRequestBody;
import com.orangetalents.orangetalentschallenge.model.request.UserPostResponseBody;
import com.orangetalents.orangetalentschallenge.util.UserCreator;
import com.orangetalents.orangetalentschallenge.util.UserPostRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserControllerIT {

    private static final String CREATE_NEW_USER_URL = "/user/sign-up";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Create new User then returns 201 status code and UserPostResponseBody if given valid UserPostRequestBody")
    void createNewUser_ReturnsUserPostResponseBodyAndStatusCode201_IfGivenValidUserPostRequestBody(){
        UserPostRequestBody validUserPostRequestBody = UserPostRequestBodyCreator.createValidUserPostRequestBody();

        ResponseEntity<UserPostResponseBody> exchange = testRestTemplate.exchange(CREATE_NEW_USER_URL, HttpMethod.POST,
                new HttpEntity<>(validUserPostRequestBody), UserPostResponseBody.class);

        Assertions.assertThat(exchange).isNotNull();
        Assertions.assertThat(exchange.getBody()).isInstanceOf(UserPostResponseBody.class);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("Create new User then returns 400 status code and ExceptionDetails if given invalid UserPostRequestBody")
    void createNewUser_ReturnsExceptionDetailsAndStatusCode400_IfGivenInvalidUserPostRequestBody(){
        UserPostRequestBody invalidUserPostRequestBody = UserPostRequestBodyCreator.createInvalidUserPostRequestBody();

        ResponseEntity<ExceptionDetails> exchange = testRestTemplate.exchange(CREATE_NEW_USER_URL, HttpMethod.POST,
                new HttpEntity<>(invalidUserPostRequestBody), ExceptionDetails.class);

        Assertions.assertThat(exchange).isNotNull();
        Assertions.assertThat(exchange.getBody()).isInstanceOf(ExceptionDetails.class);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("Create new User then returns 400 status code and ExceptionDetails if given invalid email")
    void createNewUser_ReturnsExceptionDetailsAndStatusCode400_IfGivenInvalidEmail(){
        UserPostRequestBody invalidUserPostRequestBody = UserPostRequestBodyCreator.createValidUserPostRequestBody();

        invalidUserPostRequestBody.setEmail("invalidemail");

        ResponseEntity<ExceptionDetails> exchange = testRestTemplate.exchange(CREATE_NEW_USER_URL, HttpMethod.POST,
                new HttpEntity<>(invalidUserPostRequestBody), ExceptionDetails.class);

        Assertions.assertThat(exchange).isNotNull();
        Assertions.assertThat(exchange.getBody()).isInstanceOf(ExceptionDetails.class);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("Create new User then returns 400 status code and ExceptionDetails if given invalid CPF")
    void createNewUser_ReturnsExceptionDetailsAndStatusCode400_IfGivenInvalidCPF(){
        UserPostRequestBody invalidUserPostRequestBody = UserPostRequestBodyCreator.createValidUserPostRequestBody();

        invalidUserPostRequestBody.setCpf("invalidcpf");

        ResponseEntity<ExceptionDetails> exchange = testRestTemplate.exchange(CREATE_NEW_USER_URL, HttpMethod.POST,
                new HttpEntity<>(invalidUserPostRequestBody), ExceptionDetails.class);

        ExceptionDetails exception = exchange.getBody();

        Assertions.assertThat(exchange).isNotNull();
        Assertions.assertThat(exchange.getBody()).isInstanceOf(ExceptionDetails.class);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        Assertions.assertThat(exception).isNotNull();
        Assertions.assertThat(exception.getDetails()).isEqualTo("Invalid CPF");
    }

    @Test
    @DisplayName("Create new User then returns 400 status code and ExceptionDetails if given in future birthday")
    void createNewUser_ReturnsExceptionDetailsAndStatusCode400_IfGivenFutureBirthday(){
        UserPostRequestBody invalidUserPostRequestBody = UserPostRequestBodyCreator.createValidUserPostRequestBody();

        invalidUserPostRequestBody.setBirthday(LocalDate.of(3000,1,1));

        ResponseEntity<ExceptionDetails> exchange = testRestTemplate.exchange(CREATE_NEW_USER_URL, HttpMethod.POST,
                new HttpEntity<>(invalidUserPostRequestBody), ExceptionDetails.class);

        ExceptionDetails exception = exchange.getBody();

        Assertions.assertThat(exchange).isNotNull();
        Assertions.assertThat(exchange.getBody()).isInstanceOf(ExceptionDetails.class);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        Assertions.assertThat(exception).isNotNull();
        Assertions.assertThat(exception.getDetails().trim()).isEqualTo("It must be a past date");
    }

    @Test
    @DisplayName("Create new User then returns 400 status code and ExceptionDetails if given email already exists")
    void createNewUser_ReturnsExceptionDetailsAndStatusCode400_IfEmailAlreadyExists(){
        User validUserToBeSaved = UserCreator.createValidUserToBeSaved();

        userRepository.save(validUserToBeSaved);

        UserPostRequestBody validUserPostRequestBody = UserPostRequestBodyCreator.createValidUserPostRequestBody();

        validUserPostRequestBody.setCpf("22222222222");
        validUserPostRequestBody.setEmail(validUserToBeSaved.getEmail());

        ResponseEntity<ExceptionDetails> exchange = testRestTemplate.exchange(CREATE_NEW_USER_URL, HttpMethod.POST,
                new HttpEntity<>(validUserPostRequestBody), ExceptionDetails.class);

        ExceptionDetails exceptionDetails = exchange.getBody();

        Assertions.assertThat(exchange).isNotNull();
        Assertions.assertThat(exchange.getBody()).isInstanceOf(ExceptionDetails.class);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        Assertions.assertThat(exceptionDetails).isNotNull();
        Assertions.assertThat(exceptionDetails.getDetails()).isEqualTo("Email already registered");
    }

    @Test
    @DisplayName("Create new User then returns 400 status code and ExceptionDetails if given CPF already taken")
    void createNewUser_ReturnsExceptionDetailsAndStatusCode400_IfCPFAlreadyTaken(){
        User validUserToBeSaved = UserCreator.createValidUserToBeSaved();

        userRepository.save(validUserToBeSaved);

        UserPostRequestBody validUserPostRequestBody = UserPostRequestBodyCreator.createValidUserPostRequestBody();

        validUserPostRequestBody.setCpf(validUserToBeSaved.getCpf());
        validUserPostRequestBody.setEmail("difer@test.com");

        ResponseEntity<ExceptionDetails> exchange = testRestTemplate.exchange(CREATE_NEW_USER_URL, HttpMethod.POST,
                new HttpEntity<>(validUserPostRequestBody), ExceptionDetails.class);

        ExceptionDetails exceptionDetails = exchange.getBody();

        Assertions.assertThat(exchange).isNotNull();
        Assertions.assertThat(exchange.getBody()).isInstanceOf(ExceptionDetails.class);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        Assertions.assertThat(exceptionDetails).isNotNull();
        Assertions.assertThat(exceptionDetails.getDetails()).isEqualTo("CPF already taken");
    }
}
