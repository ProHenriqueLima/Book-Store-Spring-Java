package com.api.bookstore.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_publishers")
public class PublisherModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @NotBlank(message = "Campo não informado!")
    @Column(nullable = false, unique = true, length = 64)
    public String namePublish;

    @NotBlank(message = "Campo não informado!")
    @Column(nullable = false, unique = true, length = 64)
    public String cityPublish;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
