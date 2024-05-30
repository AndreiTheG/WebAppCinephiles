package com.example.WebAppCinephiles.Models;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="Actor")
public class Actor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ActorId")
    public long actorId;

    @NonNull
    @Column(name = "name")
    public String name;
    @NonNull
    @Column(name = "birthDate")
    public String birthDate;
    @NonNull
    @Column(name = "description")
    public String description;

    public Actor(@NonNull String name, @NonNull String birthDate, @NonNull String description) {
        this.name = name;
        this.birthDate = birthDate;
        this.description = description;
    }

    //    public Actor(@NonNull String name) {
//        this.name = name;
//    }

    public Actor() {}

    public long getActorId() {
        return actorId;
    }

    public void setActorId(long actorId) {
        this.actorId = actorId;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(@NonNull String birthDate) {
        this.birthDate = birthDate;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "actorId=" + actorId +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    //    @Override
//    public String toString() {
//        return "Actor{" +
//                "name='" + name + '\'' +
//                '}';
//    }
}
