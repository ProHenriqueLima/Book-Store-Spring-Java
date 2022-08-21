package com.api.bookstore.repositories;

import com.api.bookstore.models.PublisherModel;
import com.api.bookstore.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>
{
    @Query(value = "SELECT u FROM UserModel u WHERE u.email = :email")
    UserModel verifyExistedUser(@Param("email") String email);
}
