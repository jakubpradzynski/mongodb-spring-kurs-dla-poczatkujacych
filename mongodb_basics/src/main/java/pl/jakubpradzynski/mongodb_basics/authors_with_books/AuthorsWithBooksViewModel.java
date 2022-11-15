package pl.jakubpradzynski.mongodb_basics.authors_with_books;

import pl.jakubpradzynski.mongodb_basics.authors.Author;
import pl.jakubpradzynski.mongodb_basics.books.Book;
import pl.jakubpradzynski.mongodb_basics.books.Genre;
import pl.jakubpradzynski.mongodb_basics.books.Score;

import java.util.Date;
import java.util.List;
import java.util.Set;

record AuthorsWithBooksViewModel(
        String author,
        int booksCount,
        Date dateOfBirth,
        Date dateOfDeath,
        int age,
        String nationality,
        List<BookViewModel> books
) {
    static AuthorsWithBooksViewModel fromAuthorAndBooksAndAge(Author author, List<Book> books, int age) {
        return new AuthorsWithBooksViewModel(
                author.name() + " " + author.surname(),
                books.size(),
                author.dateOfBirth(),
                author.dateOfDeath(),
                age,
                author.nationality(),
                books.stream().map(BookViewModel::fromBook).toList()
        );
    }
}

record BookViewModel(
        String title,
        String description,
        String publisher,
        int publishYear,
        int numberOfPages,
        String isbnNumber,
        Set<Genre> genres,
        Score score
) {
    static BookViewModel fromBook(Book book) {
        return new BookViewModel(
                book.title(),
                book.description(),
                book.publisher(),
                book.publishYear(),
                book.numberOfPages(),
                book.isbnNumber(),
                book.genres(),
                book.score()
        );
    }
}