package com.api.bookstore.dtos;

import com.api.bookstore.models.BookModel;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class PublisherDto {

    @NotBlank
    @Size(max = 64)
    public String namePublish;

    @NotBlank
    @Size(max = 100)
    public String cityPublish;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookModel> books;

    public String getNamePublish() {
        return namePublish;
    }

    public void setNamePublish(String namePublish) {
        this.namePublish = namePublish;
    }

    public String getCityPublish() {
        return cityPublish;
    }

    public void setCityPublish(String cityPublish) {
        this.cityPublish = cityPublish;
    }
}
