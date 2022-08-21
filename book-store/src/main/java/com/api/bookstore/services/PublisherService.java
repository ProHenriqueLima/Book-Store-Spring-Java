package com.api.bookstore.services;

import com.api.bookstore.models.BookModel;
import com.api.bookstore.models.PublisherModel;
import com.api.bookstore.models.RentModel;
import com.api.bookstore.repositories.BookRepository;
import com.api.bookstore.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PublisherService {

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    BookRepository bookRepository;

    @Transactional
    public ResponseEntity<Object> save(PublisherModel publisherModel) {
        if (!verifyNamePublisher(publisherModel.getNamePublish())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Essa Editora já é existente");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherRepository.save(publisherModel));
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
        if (!publisherModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Editora não existente");
        }
        if (!verifyPublisherBook(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Essa Editora não pode ser apagada pois existe Livros vinculados à ela. ");
        }
        publisherRepository.delete(publisherModel.get());
        return ResponseEntity.status(HttpStatus.OK).body(publisherModel.get());
    }

    private boolean verifyNamePublisher(String name)
    {
        PublisherModel publisherModel = publisherRepository.verifyExistedPublisher(name);
        if (publisherModel == null){return true;}
        else {return false;}
    }

    private boolean verifyPublisherBook(Long id)
    {
        List<BookModel> bookModel = bookRepository.verifyExistedPublisherBook(id);
        if (bookModel.size() == 0){return true;}
        else {return false;}
    }
}
