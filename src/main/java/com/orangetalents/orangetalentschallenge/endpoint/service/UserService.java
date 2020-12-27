package com.orangetalents.orangetalentschallenge.endpoint.service;

import com.orangetalents.orangetalentschallenge.endpoint.repository.UserRepository;
import com.orangetalents.orangetalentschallenge.model.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createNewUser(User user){
        return userRepository.save(user);
    }

    public Boolean existsUserByEmail(String email){
        return userRepository.existsUserByEmail(email);
    }

    public Boolean existsUserByCpf(String cpf){
        return userRepository.existsUserByCpf(cpf);
    }
}
