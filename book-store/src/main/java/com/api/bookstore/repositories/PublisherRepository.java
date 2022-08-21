package com.api.bookstore.repositories;

import com.api.bookstore.models.BookModel;
import com.api.bookstore.models.PublisherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublisherRepository extends JpaRepository<PublisherModel, Long>
{
    @Query(value = "SELECT u FROM PublisherModel u WHERE u.namePublish = :name")
    PublisherModel verifyExistedPublisher(@Param("name") String name);
}
