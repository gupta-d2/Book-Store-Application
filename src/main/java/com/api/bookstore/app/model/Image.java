package com.api.bookstore.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Image {

    @Id

    @Column(name="image_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer imageId;

    @Column(name="book_id" )
    @JsonIgnore
    private Integer bookId;

    @Lob
    @Column(name="image")
    @JsonIgnore
    private byte[] image;

    @Column(name = "create_time")
    @CreationTimestamp
    @JsonIgnore
    private Timestamp createdtime;

    @Column(name = "modify_time")
    @UpdateTimestamp
    @JsonIgnore
    private Timestamp modifytime;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @JoinColumn(name = "book_id", nullable = false , insertable=false, updatable=false)
    private Book book;

    public Image( Integer bookId, byte[] image) {
        this.bookId=bookId;
        this.image=image;
    }
}
