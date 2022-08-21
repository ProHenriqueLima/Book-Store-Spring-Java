package com.api.bookstore.repositories;

import com.api.bookstore.models.BookModel;
import com.api.bookstore.models.PublisherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long>
{

}
