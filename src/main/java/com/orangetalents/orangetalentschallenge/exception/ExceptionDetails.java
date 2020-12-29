package com.orangetalents.orangetalentschallenge.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDetails {
    @Schema(description = "Error title", example = "Method Argument Not Valid, Check the Documentation")
    private String title;
    @Schema(description = "Error status code", example = "400")
    private int status;
    @Schema(description = "Error details", example = "Email already registered")
    private String details;
    @Schema(description = "When the error happened")
    private LocalDateTime timestamp;
}
