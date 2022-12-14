package com.api.bookstore.repositories;

import com.api.bookstore.models.BookModel;
import com.api.bookstore.models.PublisherModel;
import com.api.bookstore.models.RentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long>
{
   @Query(value = "SELECT u FROM BookModel u WHERE u.name = :name")
   BookModel verifyExistedBook(@Param("name") String name);

   @Query(value = "SELECT u FROM BookModel u WHERE publisher_id = :id")
   List<BookModel> verifyExistedPublisherBook(@Param("id") Long id);

}
