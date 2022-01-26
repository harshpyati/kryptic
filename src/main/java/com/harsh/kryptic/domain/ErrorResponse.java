package com.harsh.kryptic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponse {
    String message;

    HttpStatus status;
}
