package com.example.WebAppCinephiles.Models;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name="user_data")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userId")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="imageProfileSrc")
    private String imageProfileSrc;
    @Column(name="description")
    private String description;

    public User(String name, String surname, String email, String phoneNumber, String username,
                String password, String imageProfileSrc, String description) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.imageProfileSrc = imageProfileSrc;
        this.description = description;
    }

    public User() {

    }

    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(@NonNull String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NonNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public String getImageProfileSrc() {
        return imageProfileSrc;
    }

    public void setImageProfileSrc(String imageProfileSrc) {
        this.imageProfileSrc = imageProfileSrc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", imageProfileSrc='" + imageProfileSrc + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
