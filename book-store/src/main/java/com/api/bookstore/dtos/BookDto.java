package com.api.bookstore.dtos;

import com.api.bookstore.models.PublisherModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class BookDto {

    @NotBlank(message = "Campo não informado!")
    @Column(nullable = false, unique = true)
    public String name;

    @NotNull(message = "Campo não informado!")
    public Long publisherId;

    @NotBlank(message = "Campo não informado!")
    public String author;

    @NotNull(message = "Campo não informado!")
    public int amount;

    @NotNull(message = "Campo não preenchido, informe o ano de lançamento do livro!")
    public LocalDate launch;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getLaunch() {
        return launch;
    }

    public void setLaunch(LocalDate launch) {
        this.launch = launch;
    }
}
