package com.api.bookstore.repositories;

import com.api.bookstore.models.PublisherModel;
import com.api.bookstore.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID>
{
}
