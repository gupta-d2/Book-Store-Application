package com.api.bookstore.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@JsonRootName(value = "author")
public class Author {

    @Id
    @Column(name = "author_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer authorId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "create_time")
    @CreationTimestamp
    @JsonIgnore
    private Timestamp createdtime;

    @Column(name = "modify_time")
    @UpdateTimestamp
    @JsonIgnore
    private Timestamp modifytime;

}
