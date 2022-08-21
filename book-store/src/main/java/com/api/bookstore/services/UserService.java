package com.api.bookstore.services;

import com.api.bookstore.models.PublisherModel;
import com.api.bookstore.models.UserModel;
import com.api.bookstore.repositories.PublisherRepository;
import com.api.bookstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Transactional
    public ResponseEntity<Object> update(Long id,UserModel userModel) {
        Optional<UserModel> userModelTest = userRepository.findById(id);
        if (!userModelTest.isPresent()){ return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não existente");}
        userModel.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(userModel));
    }

    public List<UserModel> getAll() { return userRepository.findAll();}

    public ResponseEntity<Object> delete(Long id) {
        Optional<UserModel> userModel = userRepository.findById(id);
        if (!userModel.isPresent()){ return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not existed");}
        userRepository.delete(userModel.get());
        return ResponseEntity.status(HttpStatus.OK).body(userModel.get());


    }

}
