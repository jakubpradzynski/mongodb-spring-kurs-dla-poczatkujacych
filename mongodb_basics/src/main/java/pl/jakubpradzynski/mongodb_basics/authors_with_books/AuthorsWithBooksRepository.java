package pl.jakubpradzynski.mongodb_basics.authors_with_books;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Repository
public class AuthorsWithBooksRepository {
    private final MongoOperations mongoOperations;

    public AuthorsWithBooksRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public List<AuthorsWithBooksViewModel> prepareAuthorsWithBooksViewModel(Instant now) {
        // TODO Not implemented yet
        // var joinAuthorsWithBooksStage =
        // var calculateBooksCountStage =
        // var calculateAgeStage =
        // var combineNameWithSurnameStage =
        // var sortAscendingByAuthorStage =
        // var projectToFinalViewStage =
        return Collections.emptyList();
    }
}
