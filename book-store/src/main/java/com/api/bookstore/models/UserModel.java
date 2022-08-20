package com.api.bookstore.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @NotBlank(message = "Campo não informado!")
    public String name;

    @Email(message = "O e-mail digitado não é válido!")
    @NotBlank(message = "Campo não informado!")
    @Column(unique = true)
    public String email;

    @NotBlank(message = "Campo não informado!")
    public String andressUser;

    @NotBlank(message = "Campo não informado!")
    public String cityUser;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAndressUser() {
        return andressUser;
    }

    public void setAndressUser(String andressUser) {
        this.andressUser = andressUser;
    }

    public String getCityUser() {
        return cityUser;
    }

    public void setCityUser(String cityUser) {
        this.cityUser = cityUser;
    }
}
