package com.harsh.kryptic.exceptions.handler;

import com.harsh.kryptic.domain.ErrorResponse;
import com.harsh.kryptic.exceptions.ApiArgumentException;
import com.harsh.kryptic.exceptions.AuthFailedException;
import com.harsh.kryptic.exceptions.InternalServerException;
import com.harsh.kryptic.exceptions.NoRecordsFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoRecordsFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleNoRecordsFoundException(NoRecordsFoundException ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleInternalServerException(NoRecordsFoundException ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApiArgumentException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorResponse handleApiArgumentException(ApiArgumentException ex){
        return new ErrorResponse(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(AuthFailedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponse handleAuthFailedException(ApiArgumentException ex){
        return new ErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
