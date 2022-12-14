package com.api.bookstore.controllers;

import com.api.bookstore.dtos.BookDto;
import com.api.bookstore.dtos.RentDto;
import com.api.bookstore.models.BookModel;
import com.api.bookstore.models.RentModel;
import com.api.bookstore.services.BookService;
import com.api.bookstore.services.RentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/rent")
@CrossOrigin(origins = "*")
public class RentController {

    @Autowired
    RentService rentService;

    @GetMapping()
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(rentService.getAll());
    }

    @PostMapping
    public ResponseEntity<Object> rentBook(@RequestBody @Valid RentDto rentDto, HttpServletResponse response) throws IOException {
        var rentModel = new RentModel();
        BeanUtils.copyProperties(rentDto , rentModel);
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=RelatorioDoAluguel.pdf";
        response.setHeader(headerKey,headerValue);
        return rentService.rentBook(rentModel,rentDto.getBookId(),rentDto.getUserId(),response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> devolutionBook(@PathVariable(value = "id" )Long id)
    {
        return rentService.devolutionBook(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleted(@PathVariable(value = "id")Long id){
        return rentService.delete(id);
    }

}
