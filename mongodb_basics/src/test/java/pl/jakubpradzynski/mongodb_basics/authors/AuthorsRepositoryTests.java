package pl.jakubpradzynski.mongodb_basics.authors;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jakubpradzynski.mongodb_basics.IntegrationTestsBase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @BeforeEach
    public void beforeEach() {
        authorsRepository.deleteAll();
        SampleAuthors.saveAllAuthors(authorsRepository);
    }
}