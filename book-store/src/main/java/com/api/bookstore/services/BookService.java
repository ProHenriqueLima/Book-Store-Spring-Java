package com.api.bookstore.services;

import com.api.bookstore.models.BookModel;
import com.api.bookstore.models.PublisherModel;
import com.api.bookstore.models.RentModel;
import com.api.bookstore.models.UserModel;
import com.api.bookstore.repositories.BookRepository;
import com.api.bookstore.repositories.PublisherRepository;
import com.api.bookstore.repositories.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    RentRepository rentRepository;

    @Autowired
    PublisherRepository publisherRepository;
    public ResponseEntity<Object> saveBook(BookModel bookModel, Long idPublisher){
            if (bookModel.getAmount() < 0){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A quantidade não pode ser menor que 0");
            }
            if (!verifyNameBook(bookModel.getName())){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse livro já é existente");
            }
            LocalDate date = LocalDate.now();
            if ((bookModel.getLaunch()).isAfter(date)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A data de lançamento não pode ser depois da data de hoje");
            }
            if (!publisherRepository.findById(idPublisher).isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A editora não foi encontrada");
            }

            Optional<PublisherModel> publisherModelFind = publisherRepository.findById(idPublisher);

            PublisherModel publisherModel = null;
            publisherModel = publisherModelFind.get();

            bookModel.setPublisher(publisherModel);
            bookRepository.save(bookModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(bookModel);


    }

    public List<BookModel> getAll() { return bookRepository.findAll();}


    public ResponseEntity<Object> update(Long id,BookModel bookModel,Long idPublisher)
    {
        if (bookModel.getAmount() < 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A quantidade não pode ser menor que 0");
        }
        if (!publisherRepository.findById(idPublisher).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A editora não foi encontrada");
        }
        Optional<BookModel> bookModelTest = bookRepository.findById(id);
        if (!bookModelTest.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não existente");
        }
        bookModel.setId(id);
        Optional<PublisherModel> publisherModelFind = publisherRepository.findById(idPublisher);
        PublisherModel publisherModel = null;
        publisherModel = publisherModelFind.get();
        bookModel.setPublisher(publisherModel);
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.save(bookModel));
    }

    private boolean verifyNameBook(String name)
    {
        BookModel bookModel = bookRepository.verifyExistedBook(name);
        if (bookModel == null){return true;}
        else {return false;}
    }

    private boolean verifyBookRent(Long id)
    {
        RentModel rentModel = rentRepository.verifyExistedRentBook(id);
        if (rentModel == null){return true;}
        else {return false;}
    }
    public ResponseEntity<Object> delete(Long id) {
        Optional<BookModel> bookModel = bookRepository.findById(id);
        if (!bookModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não existente");
        }
        if (!verifyBookRent(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse livro não pode ser apagado pois existe alugueis vinculados à ele. ");
        }
        bookRepository.delete(bookModel.get());
        return ResponseEntity.status(HttpStatus.OK).body(bookModel.get());
    }
}
