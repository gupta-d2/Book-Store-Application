package com.api.bookstore.app.exception;


import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookStoreException extends RuntimeException {

    private Integer errorCode;
    private String errorMessage;

}
