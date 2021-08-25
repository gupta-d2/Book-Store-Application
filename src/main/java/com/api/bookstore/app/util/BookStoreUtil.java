package com.api.bookstore.app.util;

import com.api.bookstore.app.dto.BookDto;
import com.api.bookstore.app.model.Author;
import com.api.bookstore.app.model.Book;

public class BookStoreUtil {

    public static Book getBookFromDto(BookDto bookDto)
    {
        Book book= new Book();
        book.setBookId(bookDto.getBookId());
        book.setLaunchDate(bookDto.getLaunchDate());
        book.setBookTitle(bookDto.getBookTitle());
        book.setQuantity(bookDto.getQuantity());
        book.setAuthor(bookDto.getAuthor());
        book.setPrice(bookDto.getPrice());
        book.setBookDescription(bookDto.getBookDescription());
        book.setImage(bookDto.getImage());
        return book;
    }

    public static Author getAuthorFromDto(BookDto bookDto)
    {
        Author author= new Author();
        if(bookDto.getAuthor()!=null) {
            author.setFirstName(bookDto.getAuthor().getFirstName());
            author.setLastName(bookDto.getAuthor().getLastName());
            author.setEmail(bookDto.getAuthor().getEmail());

            author.setAuthorId(bookDto.getAuthor().getAuthorId());
        }
        return author;
    }

    public static BookDto getBookDto(Book book, Author author)
    {
        BookDto bookDto = new BookDto();
        bookDto.setImage(book.getImage());
        bookDto.setBookId(book.getBookId());
        bookDto.setLaunchDate(book.getLaunchDate());
        bookDto.setQuantity(book.getQuantity());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setPrice(book.getPrice());
        bookDto.setBookDescription(book.getBookDescription());
        bookDto.setBookTitle(book.getBookTitle());

        return bookDto;
    }
}
