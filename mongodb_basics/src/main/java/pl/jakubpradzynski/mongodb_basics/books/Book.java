package pl.jakubpradzynski.mongodb_basics.books;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

import static pl.jakubpradzynski.mongodb_basics.books.Genre.PROGRAMMING;

enum Genre {
    INVESTMENT, PROGRAMMING
}

record Score(
        Double goodreads,
        Double lubimyczytac
) {
}

@Document(collection = "books")
public record Book(
        @Id ObjectId id,
        String title,
        @TextIndexed String description,
        String publisher,
        int publishYear,
        int numberOfPages,
        String isbnNumber,
        Set<Genre> genres,
        Score score,
        Set<ObjectId> authorIds
) {
    public static BookBuilder builder() {
        return new BookBuilder();
    }
}

class BookBuilder {
    private @Id ObjectId id;
    private String title;
    private String description;
    private String publisher;
    private int publishYear;
    private int numberOfPages;
    private String isbnNumber;
    private Set<Genre> genres;
    private Score score;
    private Set<ObjectId> authorIds;

    BookBuilder copyFrom(Book book) {
        id = book.id();
        title = book.title();
        description = book.description();
        publisher = book.publisher();
        publishYear = book.publishYear();
        numberOfPages = book.numberOfPages();
        isbnNumber = book.isbnNumber();
        genres = book.genres();
        score = book.score();
        authorIds = book.authorIds();
        return this;
    }

    BookBuilder generateId() {
        id = new ObjectId();
        return this;
    }

    BookBuilder id(ObjectId id) {
        this.id = id;
        return this;
    }

    BookBuilder title(String title) {
        this.title = title;
        return this;
    }

    BookBuilder description(String description) {
        this.description = description;
        return this;
    }

    BookBuilder publisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    BookBuilder publishYear(int publishYear) {
        this.publishYear = publishYear;
        return this;
    }

    BookBuilder numberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
        return this;
    }

    BookBuilder isbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
        return this;
    }

    BookBuilder genres(Set<Genre> genres) {
        this.genres = genres;
        return this;
    }

    BookBuilder score(Score score) {
        this.score = score;
        return this;
    }

    BookBuilder authorIds(Set<ObjectId> authorIds) {
        this.authorIds = authorIds;
        return this;
    }

    BookBuilder fillWithExampleData() {
        id = new ObjectId();
        title = "Title";
        description = "Description";
        publisher = "Publisher";
        publishYear = 2022;
        numberOfPages = 500;
        isbnNumber = "ISBN Number";
        genres = Set.of(PROGRAMMING);
        score = new Score(4.5, 4.24);
        authorIds = Set.of(new ObjectId());
        return this;
    }

    Book build() {
        return new Book(id, title, description, publisher, publishYear, numberOfPages, isbnNumber, genres, score, authorIds);
    }
}

record BooksGroupedByPublisher(
        @Field("_id") String publisher,
        Set<Book> books,
        int count
) {
}