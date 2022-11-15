package pl.jakubpradzynski.mongodb_basics.authors;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jakubpradzynski.mongodb_basics.IntegrationTestsBase;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.jakubpradzynski.mongodb_basics.authors.SampleAuthors.*;

class AuthorsRepositoryTests extends IntegrationTestsBase {

    @Autowired
    private AuthorsRepository authorsRepository;

    @Test
    void shouldCreateReadUpdateDeleteAuthor() {
        // GIVEN
        var authorId = new ObjectId();
        var author = Author.builder()
                .fillWithExampleData()
                .id(authorId)
                .build();

        // WHEN
        authorsRepository.save(author);

        // THEN
        assertEquals(author, authorsRepository.findById(authorId).get(), "Authors are not the same.");

        // GIVEN
        var editedAuthor = Author.builder()
                .copyFrom(author)
                .name("New name")
                .build();

        // WHEN
        authorsRepository.save(editedAuthor);

        // THEN
        assertEquals(editedAuthor, authorsRepository.findById(authorId).get(), "Author has not been changed.");

        // WHEN
        authorsRepository.deleteById(authorId);

        // THEN
        assertTrue(authorsRepository.findById(authorId).isEmpty(), "Author has not been deleted.");
    }

    @Test
    void shouldFindPolishOrNotLivingAuthorsSortedByDateOfBirthAscending() {
        // GIVEN
        var nationality = "Polish";
        var living = false;

        // WHEN
        var foundAuthors = authorsRepository.findByNationalityOrLiving(nationality, living);

        // THEN
        assertEquals(
                List.of(georgeClason, benjaminGraham, johnVlissides, przemyslawGerschmann, tomaszJaroszek),
                foundAuthors
        );
    }

    @Test
    void shouldCalculateAuthorAgesSortedByAgeDescending() {
        // GIVEN
        var now = Instant.parse("2022-11-15T10:15:30.00Z");

        // WHEN
        var authorWithAges = authorsRepository.calculateAuthorAges(now);

        // THEN
        assertEquals(
                List.of(
                        georgeClason.withAge(83),
                        benjaminGraham.withAge(82),
                        johnBogle.withAge(70),
                        robertMartin.withAge(70),
                        ralphJohnson.withAge(67),
                        richardHelm.withAge(62),
                        erichGamma.withAge(61),
                        marcLichtenfeld.withAge(49),
                        johnVlissides.withAge(44),
                        przemyslawGerschmann.withAge(38),
                        tomaszJaroszek.withAge(38)
                ),
                authorWithAges
        );
    }

    @BeforeEach
    public void beforeEach() {
        authorsRepository.deleteAll();
        SampleAuthors.saveAllAuthors(authorsRepository);
    }
}