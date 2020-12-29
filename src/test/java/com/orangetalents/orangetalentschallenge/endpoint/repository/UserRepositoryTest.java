package com.orangetalents.orangetalentschallenge.endpoint.repository;

import com.orangetalents.orangetalentschallenge.model.domain.User;
import com.orangetalents.orangetalentschallenge.util.UserCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Tests for UserRepository")
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Save create User whe successful")
    void save_PersistUser_WhenSuccessful(){
        User expectedUser = UserCreator.createValidUserToBeSaved();

        User savedUser = this.userRepository.save(expectedUser);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getName()).isEqualTo(expectedUser.getName());
        Assertions.assertThat(savedUser.getEmail()).isEqualTo(expectedUser.getEmail());
        Assertions.assertThat(savedUser.getCpf()).isEqualTo(expectedUser.getCpf());
        Assertions.assertThat(savedUser.getBirthday()).isEqualTo(expectedUser.getBirthday());

    }

    @Test
    @DisplayName("Check exists User by email return true if email already exists")
    void existsUserByEmail_ReturnTrue_IfEmailAlreadyExists(){
        User validUserToBeSaved = UserCreator.createValidUserToBeSaved();

        this.userRepository.save(validUserToBeSaved);

        String email = validUserToBeSaved.getEmail();

        Assertions.assertThat(this.userRepository.existsUserByEmail(email)).isTrue();
    }

    @Test
    @DisplayName("Check exists User by email return false if email does not exists")
    void existsUserByEmail_ReturnFalse_IfEmailDoesNotExists(){
        String email = "email@test.com";

        Assertions.assertThat(this.userRepository.existsUserByEmail(email)).isFalse();
    }

    @Test
    @DisplayName("Check exists User by CPF return true if CPF already taken")
    void existsUserByCpf_ReturnTrue_IfCPFAlreadyTaken(){
        User validUserToBeSaved = UserCreator.createValidUserToBeSaved();

        this.userRepository.save(validUserToBeSaved);

        String cpf = validUserToBeSaved.getCpf();

        Assertions.assertThat(this.userRepository.existsUserByCpf(cpf)).isTrue();
    }

    @Test
    @DisplayName("Check exists User by CPF return false if CPF does not taken")
    void existsUserByCpf_ReturnFalse_IfCPFDoesNotTaken(){
        String cpf = "111111111111";

        Assertions.assertThat(this.userRepository.existsUserByCpf(cpf)).isFalse();
    }
}
