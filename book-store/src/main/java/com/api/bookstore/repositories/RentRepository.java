package com.api.bookstore.repositories;

import com.api.bookstore.models.BookModel;
import com.api.bookstore.models.PublisherModel;
import com.api.bookstore.models.RentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;

@Repository
public interface RentRepository extends JpaRepository<RentModel, Long> {
    @Query(value = "SELECT u FROM RentModel u WHERE book_id = :id")
    RentModel verifyExistedRentBook(@Param("id") Long id);

    @Query(value = "SELECT u FROM RentModel u WHERE user_id = :id")
    RentModel verifyExistedRentUser(@Param("id") Long id);
}
