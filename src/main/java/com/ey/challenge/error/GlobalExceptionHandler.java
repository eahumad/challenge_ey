package com.ey.challenge.error;

import com.ey.challenge.exception.EmailExistsException;
import com.ey.challenge.exception.UserDTONotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest req) {
        String errorMessage = ex.getLocalizedMessage()!=null?ex.getLocalizedMessage():ex.toString();
        CustomError message = new CustomError(errorMessage);

        HttpStatus errorCode = HttpStatus.INTERNAL_SERVER_ERROR;
        if( ex instanceof EmailExistsException ||
                ex instanceof UserDTONotValidException
        ) {
            errorCode = HttpStatus.UNPROCESSABLE_ENTITY;
        }

        return new ResponseEntity<Object>(message, errorCode);
    }


}
