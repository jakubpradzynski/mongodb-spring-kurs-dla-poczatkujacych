package pl.jakubpradzynski.mongodb_basics.books;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface BooksRepository {

    void save(Book book);

    Optional<Book> findById(ObjectId bookId);

    void deleteById(ObjectId bookId);

    void deleteAll();

    void changeScores(ObjectId bookId, Double goodreads, Double lubimyczytac);

    void addAuthor(ObjectId bookId, ObjectId additionalAuthorId);

    List<Book> findByGenre(Genre genre);

    int countByGenre(Genre genre);

    List<Book> findByScoresBiggerThen(double goodreadsScoreThreshold, double lubimyczytacScoreThreshold);

    List<BooksGroupedByPublisher> findAllGroupedByPublisher();

    List<Book> findByTextInDescription(String text);
}

@Repository
interface SpringBooksRepository extends MongoRepository<Book, ObjectId> {
}

@Repository
class BooksRepositoryImpl implements BooksRepository {
    private final SpringBooksRepository springBooksRepository;
    private final MongoOperations mongoOperations;

    public BooksRepositoryImpl(SpringBooksRepository springBooksRepository, MongoOperations mongoOperations) {
        this.springBooksRepository = springBooksRepository;
        this.mongoOperations = mongoOperations;
    }

    @Override
    public void save(Book book) {
        // TODO Not implemented yet
    }

    @Override
    public Optional<Book> findById(ObjectId bookId) {
        // TODO Not implemented yet
        return Optional.empty();
    }

    @Override
    public void deleteById(ObjectId bookId) {
        // TODO Not implemented yet
    }

    @Override
    public void deleteAll() {
        // TODO Not implemented yet
    }

    @Override
    public void changeScores(ObjectId bookId, Double goodreads, Double lubimyczytac) {
        // TODO Not implemented yet
    }

    @Override
    public void addAuthor(ObjectId bookId, ObjectId additionalAuthorId) {
        // TODO Not implemented yet
    }

    @Override
    public List<Book> findByGenre(Genre genre) {
        // TODO Not implemented yet
        return Collections.emptyList();
    }

    @Override
    public int countByGenre(Genre genre) {
        // TODO Not implemented yet
        return 0;
    }

    @Override
    public List<Book> findByScoresBiggerThen(double goodreadsScoreThreshold, double lubimyczytacScoreThreshold) {
        // TODO Not implemented yet
        return Collections.emptyList();
    }

    @Override
    public List<BooksGroupedByPublisher> findAllGroupedByPublisher() {
        // TODO Not implemented yet
        return Collections.emptyList();
    }

    @Override
    public List<Book> findByTextInDescription(String text) {
        // TODO Not implemented yet
        return Collections.emptyList();
    }
}

