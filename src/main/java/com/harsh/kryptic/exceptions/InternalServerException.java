package com.harsh.kryptic.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class InternalServerException extends RuntimeException{
    @Getter @Setter
    String message;
}
