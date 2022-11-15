package pl.jakubpradzynski.mongodb_basics.authors;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface AuthorsRepository {
    void save(Author author);

    Optional<Author> findById(ObjectId authorId);

    void deleteById(ObjectId authorId);

    void deleteAll();

    List<Author> findByNationalityOrLiving(String nationality, boolean living);

    List<AuthorWithAge> calculateAuthorAges(Instant now);
}

@Repository
interface SpringAuthorsRepository extends MongoRepository<Author, ObjectId> {

}

@Repository
class AuthorsRepositoryImpl implements AuthorsRepository {
    private final SpringAuthorsRepository springAuthorsRepository;
    private final MongoOperations mongoOperations;

    AuthorsRepositoryImpl(SpringAuthorsRepository springAuthorsRepository, MongoOperations mongoOperations) {
        this.springAuthorsRepository = springAuthorsRepository;
        this.mongoOperations = mongoOperations;
    }


    @Override
    public void save(Author author) {
        // TODO Not implemented yet
    }

    @Override
    public Optional<Author> findById(ObjectId authorId) {
        // TODO Not implemented yet
        return Optional.empty();
    }

    @Override
    public void deleteById(ObjectId authorId) {
        // TODO Not implemented yet
    }

    @Override
    public void deleteAll() {
        // TODO Not implemented yet
    }

    @Override
    public List<Author> findByNationalityOrLiving(String nationality, boolean living) {
        // TODO Not implemented yet
        return Collections.emptyList();
    }

    @Override
    public List<AuthorWithAge> calculateAuthorAges(Instant now) {
        // TODO Not implemented yet
        return Collections.emptyList();
    }
}
