package org.adaschool.api.repository.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

public class User {

    private final String id;
    private final Date createdAt;
    private String name;
    private String lastName;
    private String email;
    private String passwordHash;

    public User(String id, String name, String lastName, String email, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = new BCryptPasswordEncoder().encode(password);
        this.createdAt = new Date();
    }

    public User(UserDto userDto) {
        this.id = null; // No se establece el id en el constructor de DTO
        this.name = userDto.getName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        this.passwordHash = new BCryptPasswordEncoder().encode(userDto.getPassword());
        this.createdAt = new Date();
    }

    // Otros getters y setters

    public String getId() {
        return id;
    }


    public Date getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void update(UserDto userDto) {
        this.name = userDto.getName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            this.passwordHash = new BCryptPasswordEncoder().encode(userDto.getPassword());
        }
        // No se modifica el id
    }




}