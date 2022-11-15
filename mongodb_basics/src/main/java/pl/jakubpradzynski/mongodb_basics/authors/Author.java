package pl.jakubpradzynski.mongodb_basics.authors;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "authors")
public record Author(
        @Id ObjectId id,
        String name,
        String surname,
        String nationality,
        Date dateOfBirth,
        Date dateOfDeath
) {
    public static AuthorBuilder builder() {
        return new AuthorBuilder();
    }

    public AuthorWithAge withAge(int age) {
        return new AuthorWithAge(name, surname, age);
    }
}

class AuthorBuilder {
    private ObjectId id;
    private String name;
    private String surname;
    private String nationality;
    private Date dateOfBirth;
    private Date dateOfDeath;

    AuthorBuilder copyFrom(Author author) {
        this.id = author.id();
        this.name = author.name();
        this.surname = author.surname();
        this.nationality = author.nationality();
        this.dateOfBirth = author.dateOfBirth();
        this.dateOfDeath = author.dateOfDeath();
        return this;
    }

    AuthorBuilder generateId() {
        this.id = new ObjectId();
        return this;
    }

    AuthorBuilder id(ObjectId id) {
        this.id = id;
        return this;
    }

    AuthorBuilder name(String name) {
        this.name = name;
        return this;
    }

    AuthorBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    AuthorBuilder nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    AuthorBuilder dateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    AuthorBuilder dateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
        return this;
    }

    AuthorBuilder fillWithExampleData() {
        this.id = new ObjectId();
        this.name = "Name";
        this.surname = "Surname";
        this.nationality = "Nationality";
        this.dateOfBirth = new Date(1L);
        this.dateOfDeath = new Date(2L);
        return this;
    }

    Author build() {
        return new Author(id, name, surname, nationality, dateOfBirth, dateOfDeath);
    }
}


record AuthorWithAge(String name, String surname, int age) {
}