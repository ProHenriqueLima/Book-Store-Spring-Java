package com.api.bookstore.controllers;

import com.api.bookstore.dtos.PublisherDto;
import com.api.bookstore.models.PublisherModel;
import com.api.bookstore.services.PublisherService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/publisher")
@CrossOrigin(origins = "*")
public class PublisherController {

    @Autowired
    PublisherService publisherService;

    @PostMapping
    public ResponseEntity<Object> savePublisher(@RequestBody @Valid PublisherDto publisherDto){
        var publisherModel = new PublisherModel();
        BeanUtils.copyProperties(publisherDto , publisherModel);
        return publisherService.save(publisherModel);

    }

    @GetMapping()
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(publisherService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleted(@PathVariable(value = "id")Long id){
        return publisherService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id")Long id , @RequestBody @Valid PublisherDto publisherDto)
    {
        var publisherModel = new PublisherModel();
        BeanUtils.copyProperties(publisherDto , publisherModel);
        return publisherService.update(id,publisherModel);
    }

}
