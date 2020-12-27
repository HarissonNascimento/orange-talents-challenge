package com.orangetalents.orangetalentschallenge.endpoint.controller;

import com.orangetalents.orangetalentschallenge.endpoint.service.UserService;
import com.orangetalents.orangetalentschallenge.model.request.UserPostRequestBody;
import com.orangetalents.orangetalentschallenge.model.request.UserPostResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(path = "/sign-up")
    public ResponseEntity<UserPostResponseBody> createNewUser(@Valid @RequestBody UserPostRequestBody user) {
        return new ResponseEntity<>(userService.createNewUser(user), HttpStatus.CREATED);
    }
}

