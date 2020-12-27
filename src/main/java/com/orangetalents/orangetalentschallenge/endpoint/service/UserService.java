package com.orangetalents.orangetalentschallenge.endpoint.service;

import com.orangetalents.orangetalentschallenge.endpoint.repository.UserRepository;
import com.orangetalents.orangetalentschallenge.mapper.UserMapper;
import com.orangetalents.orangetalentschallenge.mapper.UserPostResponseBodyMapper;
import com.orangetalents.orangetalentschallenge.model.request.UserPostRequestBody;
import com.orangetalents.orangetalentschallenge.model.request.UserPostResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserPostResponseBodyMapper userPostResponseBodyMapper;

    public UserPostResponseBody createNewUser(UserPostRequestBody userPostRequestBody) {

        var user = userMapper.toUser(userPostRequestBody);

        user = userRepository.save(user);

        return userPostResponseBodyMapper.toUserPostResponseBody(user);
    }

    public Boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    public Boolean existsUserByCpf(String cpf) {
        return userRepository.existsUserByCpf(cpf);
    }
}
