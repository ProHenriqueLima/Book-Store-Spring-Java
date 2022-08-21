package com.api.bookstore.controllers;

import com.api.bookstore.dtos.BookDto;
import com.api.bookstore.dtos.PublisherDto;
import com.api.bookstore.models.BookModel;
import com.api.bookstore.models.PublisherModel;
import com.api.bookstore.services.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/book")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping()
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleted(@PathVariable(value = "id")Long id){
        return bookService.delete(id);
    }

    @PostMapping
    public ResponseEntity<Object> saveBook(@RequestBody @Valid BookDto bookDto){
        var bookModel = new BookModel();
        BeanUtils.copyProperties(bookDto , bookModel);
        return bookService.saveBook(bookModel,bookDto.getPublisherId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id")Long id , @RequestBody @Valid BookDto bookDto)
    {
        var bookModel = new BookModel();
        BeanUtils.copyProperties(bookDto , bookModel);
        return bookService.update(id,bookModel,bookDto.getPublisherId());
    }
}
