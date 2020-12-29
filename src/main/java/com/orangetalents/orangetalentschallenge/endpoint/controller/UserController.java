package com.orangetalents.orangetalentschallenge.endpoint.controller;

import com.orangetalents.orangetalentschallenge.endpoint.service.UserService;
import com.orangetalents.orangetalentschallenge.exception.ExceptionDetails;
import com.orangetalents.orangetalentschallenge.model.request.UserPostRequestBody;
import com.orangetalents.orangetalentschallenge.model.request.UserPostResponseBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Create new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                         description = "Return UserPostResponseBody containing the new user id"),
            @ApiResponse(responseCode = "400",
                         description = "When receive invalid UserPostRequestBody",
                         content = @Content(schema = @Schema(implementation = ExceptionDetails.class)))
    })
    public ResponseEntity<UserPostResponseBody> createNewUser(@Valid @RequestBody UserPostRequestBody user) {
        return new ResponseEntity<>(userService.createNewUser(user), HttpStatus.CREATED);
    }
}

