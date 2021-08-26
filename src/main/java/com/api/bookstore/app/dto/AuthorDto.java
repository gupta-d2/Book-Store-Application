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
import javax.validation.constraints.Pattern;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "author")
public class AuthorDto {

    @JsonIgnore
    private Integer authorId;

    @ApiModelProperty(required =true, value = "This is author first name", example = "JK")
    @NotBlank(message = "First name is mandatory field")
    @Pattern(regexp="[a-zA-Z][a-zA-Z ]*",message = "First name should contains only alphabets")
    private String firstName;

    @ApiModelProperty(required =true, value = "This is author first name", example = "Rowling")
    @NotBlank(message = "Last name is mandatory field")
    @Pattern(regexp="[a-zA-Z][a-zA-Z ]*",message = "Last name should contains only alphabets")
    private String lastName;

    @ApiModelProperty(required =true, value = "This is author email", example = "jk.rowling@gmail.com")
    @Email(message = "Not a valid Email")
    @NotBlank(message = "Email is mandatory field")
    private String email;
}
