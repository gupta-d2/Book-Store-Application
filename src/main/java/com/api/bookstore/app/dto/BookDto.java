package com.api.bookstore.app.dto;

import com.api.bookstore.app.model.Image;
import com.api.bookstore.app.validation.Number;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data

@ApiModel(value = "Books")
public class BookDto {

    @ApiModelProperty(hidden = true)
    private Integer bookId;

    @ApiModelProperty(required =true, value = "This is title of book", example = "Harry Potter and the Prisoner of Azkaban")
    @NotBlank(message = "book title is mandatory field")
    private String bookTitle;

    @Size(min = 0, max = 200,message = "bookDescription should not be greater than 200 characters")
    @NotNull
    @ApiModelProperty(value = "This is book description", example = "Harry Potter and the Prisoner of Azkaban is a fantasy novel written by British author J. K. Rowling and is the third in the Harry Potter series.")
    private String bookDescription;

    @NotNull(message = "quantity is mandatory field, should be numbers only")
    @Positive(message = "quantity should be positive value")
    @Number(message = "quantity should be positive value")
    @ApiModelProperty(required =true, value = "This is the quantity of books", example = "1")
    private Integer quantity;

    @Number(decimal = true,message = "price should be positive value")
    @NotNull(message = "price is mandatory field")
    @Positive(message = "price should be positive value")
    @ApiModelProperty(required =true, value = "This is price of book", example = "150")
    private Float price;

    @ApiModelProperty(value = "This is the image for book")
    private Set<Image> image;


    @ApiParam(required =true,value = "This is the book author")
    @JsonProperty(value = "author")
    @Valid
    private AuthorDto authorDto;

    @NotNull(message = "launchDate is mandatory field")
    @ApiModelProperty(required =true, value = "This is book launched date", example = "2013-08-27")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date launchDate;

}
