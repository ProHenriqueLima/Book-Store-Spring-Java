package com.api.bookstore.dtos;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class RentDto {

    @NotNull(message = "Campo não informado!")
    public Long bookId;

    @NotNull(message = "Campo não informado!")
    public Long userId;

    @NotNull
    public LocalDate prediction;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getPrediction() {
        return prediction;
    }

    public void setPrediction(LocalDate prediction) {
        this.prediction = prediction;
    }
}
