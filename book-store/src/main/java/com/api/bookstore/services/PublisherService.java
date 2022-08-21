package com.api.bookstore.services;

import com.api.bookstore.models.PublisherModel;
import com.api.bookstore.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PublisherService {

    @Autowired
    PublisherRepository publisherRepository;

    @Transactional
    public PublisherModel save(PublisherModel publisherModel) {
        return publisherRepository.save(publisherModel);
    }

    @Transactional
    public ResponseEntity<Object> update(Long id,PublisherModel publisherModel) {
        Optional<PublisherModel> publisherModelTest = publisherRepository.findById(id);
        if (!publisherModelTest.isPresent()){ return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Editora não existente");}
        publisherModel.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(publisherRepository.save(publisherModel));
    }
    public List<PublisherModel> getAll() { return publisherRepository.findAll();}

    public ResponseEntity<Object> delete(Long id) {
        Optional<PublisherModel> publisherModel = publisherRepository.findById(id);
        if (!publisherModel.isPresent()){ return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Editora não existente");}
        publisherRepository.delete(publisherModel.get());
        return ResponseEntity.status(HttpStatus.OK).body(publisherModel.get());


    }
}
