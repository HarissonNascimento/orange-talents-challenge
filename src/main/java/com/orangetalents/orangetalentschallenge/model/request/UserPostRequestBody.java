package com.orangetalents.orangetalentschallenge.model.request;

import com.orangetalents.orangetalentschallenge.util.annotation.FieldUnique;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "This field must be filled with a valid and unregistered email",
            example = "example@email.com",
            required = true)
    private String email;
    @Size(message = "The field 'cpf' must contains 11 digits", min = 11, max = 11)
    @Pattern(message = "Invalid CPF",regexp = "[\\d]{11}")
    @FieldUnique(message = "CPF already taken")
    @Schema(description = "This field must be filled with a valid and unregistered CPF containing 11 numeric digits",
            example = "12345678901",
            required = true)
    private String cpf;
    @NotNull(message = "The field 'birthday' cannot be empty/null")
    @Past(message = "It must be a past date ")
    @Schema(description = "This field must be filled with a past date in format Year-Month-Day",
            example = "1999-01-30",
            required = true)
    private LocalDate birthday;
}
