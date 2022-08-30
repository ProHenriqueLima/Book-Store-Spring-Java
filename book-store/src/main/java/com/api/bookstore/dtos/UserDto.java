package com.api.bookstore.dtos;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Campo não informado!")
    public String name;

    @Email(message = "O e-mail digitado não é válido!")
    @NotBlank(message = "Campo não informado!")
    @Column(unique = true)
    public String email;

    @NotBlank(message = "Campo não informado!")
    public String andressUser;

    @NotBlank(message = "Campo não informado!")
    @Size(max = 100)
    public String cityUser;

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
