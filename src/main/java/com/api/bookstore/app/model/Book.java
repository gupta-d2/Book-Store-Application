package com.api.bookstore.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@ToString
@NoArgsConstructor
@Data
@Entity
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer bookId;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "book_description")
    private String bookDescription;

    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price")
    private Float price;

    @Column(name = "launch_date")
    private Date launchDate;


    @Column(name = "created_time")
    @CreationTimestamp
    private Timestamp createdtime;

    @Column(name = "modify_time")
    @UpdateTimestamp
    private Timestamp updatetime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book",fetch = FetchType.LAZY )
    private Set<Image> image=new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    Author author;


    public Book(Integer bookId, String bookTitle, String bookDescription, Integer quantity,
                Float price, Author author, Set<Image> image, Date launchDate) {
        this.bookId=bookId;
        this.bookTitle =bookTitle;
        this.image=image;
        this.bookDescription =bookDescription;
        this.quantity=quantity;
        this.price=price;
        this.author=author;
        this.launchDate=launchDate;
        
    }
}
