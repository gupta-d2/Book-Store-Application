package com.api.bookstore.app.exception;

import com.api.bookstore.app.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Slf4j
@ControllerAdvice
public class BookStoreGlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleAllBookStoreException(Exception ex) {
        log.info("Global Exception");
        Response error= new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),"");
        return new ResponseEntity<Response>(error, HttpStatus.BAD_REQUEST);

    }
}
