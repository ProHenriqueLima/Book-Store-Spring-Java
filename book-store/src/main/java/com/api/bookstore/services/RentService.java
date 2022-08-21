package com.api.bookstore.services;

import com.api.bookstore.models.BookModel;
import com.api.bookstore.models.PublisherModel;
import com.api.bookstore.models.RentModel;
import com.api.bookstore.models.UserModel;
import com.api.bookstore.repositories.BookRepository;
import com.api.bookstore.repositories.RentRepository;
import com.api.bookstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RentService {

    @Autowired
    RentRepository rentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    public List<RentModel> getAll() { return rentRepository.findAll();}

    @Transactional
    public ResponseEntity<Object> rentBook(RentModel rentModel, Long bookId, Long userId)
    {
        if ((rentModel.getDateRent()).isAfter(LocalDate.now())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A data de aluguel não pode ser depois da data de hoje");
        }

        //Added User in Rent
        if (!userRepository.findById(userId).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Usuário não foi encontrado");
        }
        Optional<UserModel> userModelFind = userRepository.findById(userId);
        UserModel userModel = null;
        userModel = userModelFind.get();
        rentModel.setUser(userModel);

        //Added Book in Rent
        if (!bookRepository.findById(bookId).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Livro não foi encontrado");
        }
        Optional<BookModel> bookModelFind = bookRepository.findById(bookId);
        BookModel bookModel = null;
        bookModel = bookModelFind.get();
        if (bookModel.getAmount() <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Livro não disponível para alugar");
        }
        rentModel.setBook(bookModel);
        bookModel.setAmount((bookModel.getAmount())+1);
        bookRepository.save(bookModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(rentRepository.save(rentModel));
    }

    @Transactional
    public ResponseEntity<Object> devolutionBook(Long rentId)
    {
        Optional<RentModel> rentModelFind = rentRepository.findById(rentId);
        if (!rentModelFind.isPresent()){ return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluguel não existente");}
        RentModel rentModel = null;
        rentModel = rentModelFind.get();
        rentModel.setDateDevolution(LocalDate.now());

        //Devolution book
        BookModel bookModel = rentModel.getBook();
        bookModel.setAmount((bookModel.getAmount())+1);
        bookRepository.save(bookModel);

        return ResponseEntity.status(HttpStatus.OK).body(rentRepository.save(rentModel));
    }

    public ResponseEntity<Object> delete(Long id) {
        Optional<RentModel> rentModel = rentRepository.findById(id);
        if (!rentModel.isPresent()){ return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluguel não existente");}
        if (((rentModel.get()).getDateDevolution()) == null){ return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aluguel não foi devolvido ainda, então não pode ser apagado");}
        rentRepository.delete(rentModel.get());
        return ResponseEntity.status(HttpStatus.OK).body(rentModel.get());
    }
}
