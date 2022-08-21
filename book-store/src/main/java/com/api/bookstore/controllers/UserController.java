package com.api.bookstore.controllers;

import com.api.bookstore.dtos.PublisherDto;
import com.api.bookstore.dtos.UserDto;
import com.api.bookstore.models.PublisherModel;
import com.api.bookstore.models.UserModel;
import com.api.bookstore.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto){
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto , userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));

    }

    @GetMapping()
    public ResponseEntity<Object> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletedUser(@PathVariable(value = "id")Long id){
        return userService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id")Long id , @RequestBody @Valid UserDto userDto)
    {
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto , userModel);
        return userService.update(id,userModel);
    }
}
