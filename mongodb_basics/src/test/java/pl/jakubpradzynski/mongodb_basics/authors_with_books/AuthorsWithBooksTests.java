package pl.jakubpradzynski.mongodb_basics.authors_with_books;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jakubpradzynski.mongodb_basics.IntegrationTestsBase;
import pl.jakubpradzynski.mongodb_basics.authors.AuthorsRepository;
import pl.jakubpradzynski.mongodb_basics.authors.SampleAuthors;
import pl.jakubpradzynski.mongodb_basics.books.BooksRepository;
import pl.jakubpradzynski.mongodb_basics.books.SampleBooks;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.jakubpradzynski.mongodb_basics.authors.SampleAuthors.*;
import static pl.jakubpradzynski.mongodb_basics.authors_with_books.AuthorsWithBooksViewModel.fromAuthorAndBooksAndAge;
import static pl.jakubpradzynski.mongodb_basics.books.SampleBooks.*;

class AuthorsWithBooksTests extends IntegrationTestsBase {

    @Autowired
    private AuthorsRepository authorsRepository;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private AuthorsWithBooksRepository authorsWithBooksRepository;

    @Test
    void shouldPrepareViewModelForAuthorWithBooks() {
        // GIVEN
        var now = Instant.parse("2022-11-15T10:15:30.00Z");

        // WHEN
        var result = authorsWithBooksRepository.prepareAuthorsWithBooksViewModel(now);

        // THEN
        assertEquals(List.of(
                fromAuthorAndBooksAndAge(benjaminGraham, List.of(inteligentnyInwestor), 82),
                fromAuthorAndBooksAndAge(erichGamma, List.of(wzorceProjektowe), 61),
                fromAuthorAndBooksAndAge(georgeClason, List.of(najbogatszyCzlowiekWBabilonie), 83),
                fromAuthorAndBooksAndAge(johnBogle, List.of(malaKsiazkaZdroworozsadkowegoInwestowania), 70),
                fromAuthorAndBooksAndAge(johnVlissides, List.of(wzorceProjektowe), 44),
                fromAuthorAndBooksAndAge(marcLichtenfeld, List.of(zbijFortuneNaDywidendach), 49),
                fromAuthorAndBooksAndAge(przemyslawGerschmann, List.of(sladamiWarrenaBuffeta), 38),
                fromAuthorAndBooksAndAge(ralphJohnson, List.of(wzorceProjektowe), 67),
                fromAuthorAndBooksAndAge(richardHelm, List.of(wzorceProjektowe), 62),
                fromAuthorAndBooksAndAge(robertMartin, List.of(czystyKod, mistrzCzystegoKodu), 70),
                fromAuthorAndBooksAndAge(tomaszJaroszek, List.of(sladamiWarrenaBuffeta), 38)
        ), result);
    }



    @BeforeEach
    public void beforeEach() {
        authorsRepository.deleteAll();
        booksRepository.deleteAll();
        SampleAuthors.saveAllAuthors(authorsRepository);
        SampleBooks.saveAllBooks(booksRepository);
    }
}