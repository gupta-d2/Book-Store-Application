package com.api.bookstore.app.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response {

    private int statusCode;
    private String message;
    @JsonProperty(value = "books")
    private Object bookDto;
}
