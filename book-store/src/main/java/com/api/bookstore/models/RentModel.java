package com.api.bookstore.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tb_rents")
public class RentModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @ManyToOne
    public BookModel book;

    @ManyToOne
    public UserModel user;

    public LocalDate dateDevolution;

    @NotNull
    public LocalDate dateRent;

    @NotNull
    public LocalDate prediction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookModel getBook() {
        return book;
    }

    public void setBook(BookModel book) {
        this.book = book;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public LocalDate getDateDevolution() {
        return dateDevolution;
    }

    public void setDateDevolution(LocalDate dateDevolution) {
        this.dateDevolution = dateDevolution;
    }

    public LocalDate getDateRent() {
        return dateRent;
    }

    public void setDateRent(LocalDate dateRent) {
        this.dateRent = dateRent;
    }

    public LocalDate getPrediction() {
        return prediction;
    }

    public void setPrediction(LocalDate prediction) {
        this.prediction = prediction;
    }
}
