package com.api.bookstore.app.repository;

import com.api.bookstore.app.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ImageRepository  extends JpaRepository<Image, Integer> {

}
