package com.api.bookstore.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PublisherDto {

    @NotBlank
    @Size(max = 64)
    public String namePublish;

    @NotBlank
    @Size(max = 64)
    public String cityPublish;

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
