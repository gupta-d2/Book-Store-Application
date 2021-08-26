package com.api.bookstore.app.service;

import com.api.bookstore.app.dto.BookDto;
import com.api.bookstore.app.exception.BookStoreException;
import com.api.bookstore.app.model.Author;
import com.api.bookstore.app.model.Book;
import com.api.bookstore.app.model.Image;
import com.api.bookstore.app.repository.AuthorRepository;
import com.api.bookstore.app.repository.BookRepository;
import com.api.bookstore.app.repository.ImageRepository;
import com.api.bookstore.app.util.BookStoreUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookStoreService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    ImageRepository imageRepository;

    public BookDto addBook(BookDto bookDto) throws IOException {

        log.info("bookDto: " + bookDto);

        Author author = BookStoreUtil.getAuthorFromDto(bookDto);
        log.info("author: " + author);

        if (author != null) {
            author = authorRepository.save(author);
        }
        Book book = BookStoreUtil.getBookFromDto(bookDto);
        book.setAuthor(author);
        Book addedBook = bookRepository.save(
                new Book(book.getBookId(), book.getBookTitle(), book.getBookDescription(), book.getQuantity(),
                        book.getPrice(), book.getAuthor(), null, book.getLaunchDate()));

        Set<Image> newImage =
                bookDto.getImage().stream().map(image -> {
                            image.setBookId(addedBook.getBookId());
                            return image;
                        }
                ).collect(Collectors.toSet());

        Set<Image> addedImages = imageRepository.saveAll(newImage).stream().collect(Collectors.toSet());
        log.info("Added Image: " + addedImages);
        addedBook.setAuthor(author);
        addedBook.setImage(addedImages);

        log.info("Added Book: " + addedBook);

        return BookStoreUtil.getBookDto(addedBook);
    }


    public Image upload(MultipartFile file, Integer bookId) throws IOException {

        if (!bookRepository.findById(bookId).isPresent()) {
            throw new BookStoreException(HttpStatus.NO_CONTENT.value(), "BookId doesn't not exists");
        }
        return imageRepository.save(new Image(bookId, file.getBytes()));
    }

    public void deleteBookById(Integer bookId) {


        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent())
            bookRepository.deleteById(bookId);
        else
            throw new BookStoreException(HttpStatus.NO_CONTENT.value(), "Book(BookID=" + bookId + ") Doesn't Exist.");

        Optional<Author> author = Optional.empty();
        if (book.isPresent())
            author = authorRepository.findById(book.get().getAuthor().getAuthorId());

        if (author.isPresent())
            authorRepository.deleteById(author.get().getAuthorId());

    }


    public List<BookDto> getAllBooks(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        List<Book> allBooks = bookRepository.findAll();
        Page<Book> pagedResult = bookRepository.findAll(paging);
        log.info("pagedResult.getTotalElements(): " + pagedResult.getTotalElements());
        log.info("pagedResult.getTotalPages(): " + pagedResult.getTotalPages());
        log.info("pagedResult.getNumberOfElements(): " + pagedResult.getNumberOfElements());
        if (pagedResult.hasContent()) {
            allBooks = pagedResult.getContent();
        }

        List<BookDto> bookDtoList = allBooks.stream()
                .peek(System.out::println)
                .map(book ->
                {

                    return new BookDto(book.getBookId(), book.getBookTitle(), book.getBookDescription(), book.getQuantity(),
                            book.getPrice(), book.getImage(), BookStoreUtil.getAuthor(book.getAuthor()), book.getLaunchDate());

                })
                .peek(System.out::println)
                .collect(Collectors.toList());


        return bookDtoList;
    }


    public BookDto updateBook(Integer bookId, BookDto bookDto) {
        Optional<Book> optBook = bookRepository.findById(bookId);
        bookDto.setBookId(bookId);
        Book book = null;
        if (optBook.isPresent()) {
            book = optBook.get();
            book.setBookTitle(bookDto.getBookTitle());
            book.setBookDescription(bookDto.getBookDescription());
            book.setImage(book.getImage());
            book.setPrice(bookDto.getPrice());

            book.setQuantity(bookDto.getQuantity());
            book.setLaunchDate(bookDto.getLaunchDate());
            Author author = book.getAuthor();
            author.setFirstName(bookDto.getAuthorDto().getFirstName());
            author.setLastName(bookDto.getAuthorDto().getLastName());
            author.setEmail(bookDto.getAuthorDto().getEmail());

            book.setAuthor(author);


        }

        Book updatedBook = bookRepository.save(book);

        Set<Image> newImage =
                bookDto.getImage().stream().map(image -> {
                            image.setBookId(updatedBook.getBookId());
                            return image;
                        }
                ).collect(Collectors.toSet());

        Set<Image> addedImages = imageRepository.saveAll(newImage).stream().collect(Collectors.toSet());
        log.info("Added Image: " + addedImages);

        return bookDto;

    }


    public List<BookDto> getAllBooks() {

        List<Book> allBooks = bookRepository.findAll();

        List<BookDto> bookDtoList = allBooks.stream()
                .peek(System.out::println)

                .map(book ->
                {

                    return new BookDto(book.getBookId(), book.getBookTitle(), book.getBookDescription(), book.getQuantity(),
                            book.getPrice(), book.getImage(), BookStoreUtil.getAuthor(book.getAuthor()), book.getLaunchDate());

                })
                .peek(System.out::println)
                .collect(Collectors.toList());

        return bookDtoList;
    }

    public BookDto getBookById(Integer bookId) {


        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (bookOptional.isPresent()) {
            return BookStoreUtil.getBookDto(bookOptional.get(), bookOptional.get().getAuthor());
        }

        return null;
    }
}
