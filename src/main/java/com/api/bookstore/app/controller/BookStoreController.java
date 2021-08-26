package com.api.bookstore.app.controller;

import com.api.bookstore.app.dto.BookDto;
import com.api.bookstore.app.exception.BookStoreException;
import com.api.bookstore.app.model.Image;
import com.api.bookstore.app.response.Response;
import com.api.bookstore.app.service.BookStoreService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
@Slf4j
@RestController
@RequestMapping(value = "books")
public class BookStoreController {

    @Autowired
    BookStoreService bookStoreService;

    @ApiOperation(value = "Api to get all books", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping()
    public ResponseEntity<Response> getAllBooks(@RequestParam(defaultValue = "0") Integer pageNo,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @RequestParam(defaultValue = "bookId") String sortBy)
    {
        List<BookDto> bookDto=bookStoreService.getAllBooks(pageNo,pageSize,sortBy);

        return new ResponseEntity<>(new Response(HttpStatus.OK.value(),"List of All Books",bookDto),HttpStatus.CREATED);
    }


    @ApiOperation(value = "Api to get all books", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping(value = "{bookId}", name = "bookId")
    public ResponseEntity<Response> getAllBooks(@ApiParam(required = true,defaultValue = "10") @PathVariable Integer bookId)
    {
        BookDto bookDto=bookStoreService.getBookById(bookId);
        String responseMsg=  bookDto!=null ?"Fetched the book by Id":"No Books Found";
        return new ResponseEntity<>(new Response(HttpStatus.OK.value(), responseMsg,bookDto!=null?bookDto:""),HttpStatus.CREATED);
    }

    @ApiOperation(value = "Api to add new book", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @PostMapping
    public ResponseEntity<Response> addBook(@ApiParam(required = true,
           // value ="{\"author\":{\"email\":\"d2@gmai.com\",\"firstName\":\"dddd\",\"lastName\":\"ssss\"},\"bookDescription\":\"deada\",\"bookId\":0,\"bookTitle\":\"s\",\"image\":[{\"imageId\":0}],\"launchDate\":\"2021-08-26T14:53:34.760Z\",\"price\":10,\"quantity\":1}" ,
            example = "{\"author\":{\"email\":\"d2@gmai.com\",\"firstName\":\"dddd\",\"lastName\":\"ssss\"},\"bookDescription\":\"edaxa\",\"bookId\":0,\"bookTitle\":\"s\",\"image\":[{\"imageId\":0}],\"launchDate\":\"2021-08-26T14:53:34.760Z\",\"price\":10,\"quantity\":1}",
            defaultValue = "{\"author\":{\"email\":\"d2@gmai.com\",\"firstName\":\"dddd\",\"lastName\":\"ssss\"},\"bookDescription\":\"bookos\",\"bookId\":0,\"bookTitle\":\"s\",\"image\":[{\"imageId\":0}],\"launchDate\":\"2021-08-26T14:53:34.760Z\",\"price\":10,\"quantity\":1}") @Valid @RequestBody BookDto bookDto, BindingResult bindingResult) throws IOException {

        if(bindingResult.hasErrors()) {
            Response response =new Response(HttpStatus.UNPROCESSABLE_ENTITY.value(), bindingResult.getAllErrors().get(0).getDefaultMessage(), "");
            throw new BookStoreException(HttpStatus.UNPROCESSABLE_ENTITY.value(),bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        BookDto addedBookDto = bookStoreService.addBook(bookDto);

        return new ResponseEntity<>(new Response(HttpStatus.CREATED.value(),"Book Added Successfully",addedBookDto),HttpStatus.CREATED);
    }

    @ApiOperation(value = "Api to delete the book by bookId", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @DeleteMapping(value = "{bookId}", name = "bookId")
    public ResponseEntity<Response> deleteBook(@ApiParam(required = true,defaultValue = "12") @PathVariable Integer bookId) throws IOException {


        bookStoreService.deleteBookById(bookId);

        return new ResponseEntity<>(new Response(HttpStatus.OK.value(), "Book Deleted Successfully",""),HttpStatus.OK);
    }

    @ApiOperation(value = "Api to upload the book", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @PostMapping(value = "/upload-book-image/{bookId}")
    public ResponseEntity<Response> upload(@PathVariable Integer bookId,@RequestParam("imageFile") MultipartFile file) throws IOException {

        log.info("data: "+ file.getBytes());
        Image image=bookStoreService.upload(file,bookId);
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.value(),"Image Uploaded for Book (BookId="+ bookId+")",image),HttpStatus.CREATED);
    }


    @ApiOperation(value = "Api to get book by bookId", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @PutMapping(value = "{bookId}")
    public ResponseEntity<Response> updateBook(@ApiParam(
            name =  "bookId",
            value = "Book Id is required to update the book",
            example = "123",

            required = true)@PathVariable Integer bookId,
           @ApiParam(
                   name =  "book",
                   value = "This is the Book Object",
                   defaultValue = "{\"author\":{\"email\":\"d2@gmai.com\",\"firstName\":\"dddd\",\"lastName\":\"ssss\"},\"bookDescription\":\"bookos\",\"bookId\":0,\"bookTitle\":\"s\",\"image\":[{\"imageId\":0}],\"launchDate\":\"2021-08-26T14:53:34.760Z\",\"price\":10,\"quantity\":1}",
                   required = true)
           @Valid @RequestBody BookDto bookDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            Response response =new Response(HttpStatus.UNPROCESSABLE_ENTITY.value(), bindingResult.getAllErrors().get(0).getDefaultMessage(), "");
            throw new BookStoreException(HttpStatus.UNPROCESSABLE_ENTITY.value(),bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        BookDto updatedBookDto = bookStoreService.updateBook(bookId,bookDto);

        return new ResponseEntity<>(new Response(HttpStatus.OK.value(),"Book Updated Successfully",updatedBookDto),HttpStatus.CREATED);
    }



}

