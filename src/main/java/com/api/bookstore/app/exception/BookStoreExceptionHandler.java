package com.api.bookstore.app.exception;

import com.api.bookstore.app.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookStoreExceptionHandler {

    @ExceptionHandler(BookStoreException.class)
    public ResponseEntity<Response> handleBookStoreException(BookStoreException ex) {

        Response error= new Response(ex.getErrorCode(),ex.getErrorMessage(),"");
        return new ResponseEntity<Response>(error, HttpStatus.BAD_GATEWAY);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleAllException(BookStoreException ex) {

        Response error= new Response(ex.getErrorCode(),ex.getErrorMessage(),"");
        return new ResponseEntity<Response>(error, HttpStatus.BAD_GATEWAY);

    }
}

