package com.orangetalents.orangetalentschallenge.exception;

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
    private String title;
    private int status;
    private String details;
    private LocalDateTime timestamp;
}
