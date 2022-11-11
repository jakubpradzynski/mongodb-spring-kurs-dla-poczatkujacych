package pl.jakubpradzynski.mongodb_basics.books;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jakubpradzynski.mongodb_basics.IntegrationTestsBase;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.jakubpradzynski.mongodb_basics.books.Genre.INVESTMENT;
import static pl.jakubpradzynski.mongodb_basics.books.Genre.PROGRAMMING;
import static pl.jakubpradzynski.mongodb_basics.books.SampleBooks.*;

class BooksRepositoryTests extends IntegrationTestsBase {

    @Autowired
    private BooksRepository booksRepository;

    @Test
    void shouldCreateReadUpdateDeleteBook() {
        // GIVEN
        var bookId = new ObjectId();
        var book = Book.builder()
                .fillWithExampleData()
                .id(bookId)
                .build();

        // WHEN
        booksRepository.save(book);

        // THEN
        assertEquals(book, booksRepository.findById(bookId).get(), "Books are not the same.");

        // GIVEN
        var editedBook = Book.builder()
                .copyFrom(book)
                .title("New title")
                .build();

        // WHEN
        booksRepository.save(editedBook);

        // THEN
        assertEquals(editedBook, booksRepository.findById(bookId).get(), "Book has not been changed.");

        // WHEN
        booksRepository.deleteById(bookId);

        // THEN
        assertTrue(booksRepository.findById(bookId).isEmpty(), "Book has not been deleted.");
    }

    @Test
    void shouldEditBookScore() {
        // GIVEN
        var bookId = new ObjectId();
        var book = Book.builder()
                .fillWithExampleData()
                .id(bookId)
                .score(new Score(1.0, 1.0))
                .build();
        booksRepository.save(book);

        // WHEN
        booksRepository.changeScores(bookId, 5.0, 5.0);

        // THEN
        assertEquals(
                new Score(5.0, 5.0),
                booksRepository.findById(bookId).get().score(),
                "Scores have not been changed."
        );
    }

    @Test
    void shouldAddNewAuthor() {
        // GIVEN
        var bookId = new ObjectId();
        var mainAuthorId = new ObjectId();
        var additionalAuthorId = new ObjectId();
        var book = Book.builder()
                .fillWithExampleData()
                .id(bookId)
                .authorIds(Set.of(mainAuthorId))
                .build();
        booksRepository.save(book);

        // WHEN
        booksRepository.addAuthor(bookId, additionalAuthorId);

        // THEN
        assertEquals(
                Set.of(mainAuthorId, additionalAuthorId),
                booksRepository.findById(bookId).get().authorIds(),
                "Author has not been added."
        );
    }

    @Test
    void shouldFindAllProgrammingBooks() {
        // GIVEN

        // WHEN
        var foundBooks = booksRepository.findByGenre(PROGRAMMING);

        // THEN
        assertEquals(
                List.of(czystyKod, mistrzCzystegoKodu, wzorceProjektowe),
                foundBooks
        );
    }

    @Test
    void shouldCountAllInvestingBooks() {
        // GIVEN

        // WHEN
        var count = booksRepository.countByGenre(INVESTMENT);

        // THEN
        assertEquals(count, 5);
    }

    @BeforeEach
    public void beforeEach() {
        booksRepository.deleteAll();
        booksRepository.save(zbijFortuneNaDywidendach);
        booksRepository.save(inteligentnyInwestor);
        booksRepository.save(malaKsiazkaZdroworozsadkowegoInwestowania);
        booksRepository.save(sladamiWarrenaBuffeta);
        booksRepository.save(najbogatszyCzlowiekWBabilonie);
        booksRepository.save(czystyKod);
        booksRepository.save(mistrzCzystegoKodu);
        booksRepository.save(wzorceProjektowe);
    }
}