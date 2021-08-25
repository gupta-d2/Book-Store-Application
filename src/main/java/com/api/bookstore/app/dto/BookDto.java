package com.api.bookstore.app.dto;

import com.api.bookstore.app.model.Author;
import com.api.bookstore.app.model.Image;
import com.api.bookstore.app.validation.Number;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.io.File;
import java.util.Date;
import java.util.Set;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonRootName(value = "books")
public class BookDto {

    private Integer bookId;

    @NotBlank(message = "book title is mandatory field")
    private String bookTitle;

    @Size(min = 0, max = 200,message = "bookDescription should not be greater than 200 characters")
    @NotNull
    private String bookDescription;

    @NotNull(message = "quantity is mandatory field, should be numbers only")
    @Positive(message = "quantity should be positive value")
    @Number(message = "quantity should be positive value")
    private Integer quantity;

    @Number(decimal = true,message = "price should be positive value")
    @NotNull(message = "price is mandatory field")
    @Positive(message = "price should be positive value")
    private Float price;

    private Set<Image> image;

    private Author author;

    @NotNull(message = "launchDate is mandatory field")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date launchDate;

}
