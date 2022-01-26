package com.harsh.kryptic.exceptions;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class NoRecordsFoundException extends RuntimeException {
    @Getter
    @Setter
    String message;
}
