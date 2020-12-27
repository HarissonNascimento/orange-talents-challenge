package com.orangetalents.orangetalentschallenge.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPostResponseBody {
    @Schema(description = "This field represents the user id that was just created", example = "10")
    private Long id;
}
