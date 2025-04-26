package com.capgeminu.api_ordermaintenance.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Default API error response")
public record ErrorDto(@Schema(description = "Code error", example = "500") int status,
                       @Schema(description = "Message error") String message) {
}