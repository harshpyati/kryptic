package com.harsh.kryptic.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
public class AuthFailedException extends RuntimeException{
    @Getter @Setter String message;
}
