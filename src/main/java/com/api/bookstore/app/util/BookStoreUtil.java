package com.api.bookstore.app.util;

import com.api.bookstore.app.dto.AuthorDto;
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
        book.setAuthor(getAuthorFromDto(bookDto.getAuthorDto()));
        book.setPrice(bookDto.getPrice());
        book.setBookDescription(bookDto.getBookDescription());
        book.setImage(bookDto.getImage());
        return book;
    }

    public static Author getAuthorFromDto(BookDto bookDto)
    {
        Author author= new Author();
        if(bookDto.getAuthorDto()!=null) {
            author.setFirstName(bookDto.getAuthorDto().getFirstName());
            author.setLastName(bookDto.getAuthorDto().getLastName());
            author.setEmail(bookDto.getAuthorDto().getEmail());

            author.setAuthorId(bookDto.getAuthorDto().getAuthorId());
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
        bookDto.setAuthorDto(getAuthor(book.getAuthor()));
        bookDto.setPrice(book.getPrice());
        bookDto.setBookDescription(book.getBookDescription());
        bookDto.setBookTitle(book.getBookTitle());

        return bookDto;
    }

    public static Author getAuthorFromDto(AuthorDto authorDto)
    {
        Author author = new Author();
        author.setEmail(authorDto.getEmail());
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());

        return author;
    }

    public static AuthorDto getAuthor(Author author)
    {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setEmail(author.getEmail());
        authorDto.setFirstName(author.getFirstName());
        authorDto.setLastName(author.getLastName());

        return authorDto;
    }

    public static BookDto getBookDto(Book book)
    {
        BookDto bookDto = new BookDto();
        bookDto.setImage(book.getImage());
        bookDto.setBookId(book.getBookId());
        bookDto.setLaunchDate(book.getLaunchDate());
        bookDto.setQuantity(book.getQuantity());
        bookDto.setAuthorDto(getAuthor(book.getAuthor()));
        bookDto.setPrice(book.getPrice());
        bookDto.setBookDescription(book.getBookDescription());
        bookDto.setBookTitle(book.getBookTitle());

        return bookDto;
    }


}
