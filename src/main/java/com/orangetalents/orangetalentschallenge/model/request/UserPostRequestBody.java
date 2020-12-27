package com.orangetalents.orangetalentschallenge.model.request;

import com.orangetalents.orangetalentschallenge.util.annotation.FieldUnique;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class UserPostRequestBody {
    @NotEmpty(message = "The field 'email' cannot be empty/null")
    @Email
    @FieldUnique(message = "Email already registered")
    private String email;
    @Size(message = "The field 'cpf' must contains 11 digits", min = 11, max = 11)
    @Pattern(message = "Invalid CPF",regexp = "[\\d]{11}")
    @FieldUnique(message = "CPF already taken")
    private String cpf;
    @NotEmpty(message = "The field 'birthday' cannot be empty/null")
    private LocalDate birthday;
}
