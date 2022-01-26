package com.harsh.kryptic.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor @NoArgsConstructor
public class ApiArgumentException extends RuntimeException{
    @Getter @Setter  String message;
}
