package com.api.bookstore.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "author")
public class AuthorDto {

    @JsonIgnore
    private Integer authorId;

    @ApiModelProperty(required =true, value = "This is author first name", example = "J. K.")
    @NotBlank(message = "book first name is mandatory field")
    private String firstName;

    @ApiModelProperty(required =true, value = "This is author first name", example = "Rowling")
    @NotBlank(message = "book last name is mandatory field")
    private String lastName;

    @ApiModelProperty(required =true, value = "This is author email", example = "jk.rowling@gmail.com")
    @Email(message = "Not a valid Email")
    @NotBlank(message = "book First is mandatory field")
    private String email;
}
