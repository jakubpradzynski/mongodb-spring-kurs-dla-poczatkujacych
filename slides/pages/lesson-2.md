# Zajęcia 2 (Projekt ze [Spring Data MongoDB](https://spring.io/projects/spring-data-mongodb))

<br>
Na tych zajęciach skupimy się na operowaniu na bazie MongoDB z projektu: Java + Spring Framework.

Twoim zadaniem będzie zaimplementować metody w repozytoriach tak, by wszystkie testy przeszły.

---

# Spring Framework - wprowadzenie

<br>

1. Spring Framework.
2. Spring Boot.
3. Spring Data.
4. Spring Data MongoDB.

---

# Konfiguracja połącznia do MongoDB z projektu

<br>

Zależność: `org.springframework.boot:spring-boot-starter-data-mongodb`

Propery z MongoURI: `spring.data.mongodb.uri`

---

# MongoRepository<T, ID>

<br>

Czym jest `MongoRepository`?

<br>

Przykład:

```java
@Repository
interface StudentsRepository implements MongoRepository<Student, ObjectId> {
    Optional<Student> findByNameAndSurname(String name, String surname);
}
```

---

# MongoOperations

<br>

Czym jest `MongoOperations`?

Przykład:

```java
@Repository
class StudentsRepository {
    private final MongoOperations mongoOperations;

    StudentsRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public List<Students> findAllStudentsWithName(String name) {
        return mongoOperations.find(
                Query.query(Criteria.where("name").is(name)),
                Student.class
        );
    }
}
```

---

# Klasa bazodanowa

<br>

Istotne adnotacje: `@Document`, `@Id`, `@Field`, `@Indexed`, `@CompoundIndex`

```java
@Document(collection = "students")
record Student(
        @Id ObjectId id,
        @Field("firstName") String name,
        @Field("lastName") String surname
) {
}
```

---

# Projekt

<br>

Pobierz projekt z repo: https://github.com/jakubpradzynski/mongodb-spring-kurs-dla-poczatkujacych

<br>

Wiedza i zadania do wykonania z podpowiedziami na
wiki (punkt 6 i 7): https://github.com/jakubpradzynski/mongodb-spring-kurs-dla-poczatkujacych/wiki

---
layout: center
---

# BooksRepository

---
layout: center
---

### Test 1 - `shouldCreateReadUpdateDeleteBook`

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```java

@Repository
class BooksRepositoryImpl implements BooksRepository {
    @Override
    public void save(Book book) {
        springBooksRepository.save(book);
    }

    @Override
    public Optional<Book> findById(ObjectId bookId) {
        return springBooksRepository.findById(bookId);
    }

    @Override
    public void deleteById(ObjectId bookId) {
        springBooksRepository.deleteById(bookId);
    }

    @Override
    public void deleteAll() {
        springBooksRepository.deleteAll();
    }
}
```

</details>

<br>

##### Pamiętaj o zaimplementowaniu również metody `deleteAll`, która jest używana w metodzie `beforeEach` przygotowującej dane do testów.

---
layout: center
---

### Test 2 - `shouldEditBookScore`

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```java

@Repository
class BooksRepositoryImpl implements BooksRepository {
    @Override
    public void changeScores(ObjectId bookId, Double goodreads, Double lubimyczytac) {
        mongoOperations.updateFirst(
                Query.query(Criteria.where("_id").is(bookId)),
                Update.update("score.goodreads", goodreads).set("score.lubimyczytac", lubimyczytac),
                Book.class
        );
    }
}
```

</details>

---
layout: center
---

### Test 3 - `shouldAddNewAuthor`

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```java

@Repository
class BooksRepositoryImpl implements BooksRepository {
    @Override
    public void addAuthor(ObjectId bookId, ObjectId additionalAuthorId) {
        mongoOperations.updateFirst(
                Query.query(Criteria.where("_id").is(bookId)),
                new Update().addToSet("authorIds", additionalAuthorId),
                Book.class
        );
    }
}
```

</details>

---
layout: center
---

### Test 4 - `shouldFindAllProgrammingBooks`

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```java

@Repository
class BooksRepositoryImpl implements BooksRepository {
    @Override
    public List<Book> findByGenre(Genre genre) {
        return mongoOperations.find(
                Query.query(Criteria.where("genres").in(genre)),
                Book.class
        );
    }
}
```

</details>

---
layout: center
---

### Test 5 - `shouldCountAllInvestingBooks`

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```java

@Repository
class BooksRepositoryImpl implements BooksRepository {
    @Override
    public int countByGenre(Genre genre) {
        return springBooksRepository.countBooksByGenresIn(genre);
    }
}
```

</details>

---
layout: center
---

### Test 6 - `shouldFindAllBooksWithScoresBiggerThenGivenAndSortedAscending`

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```java

@Repository
class BooksRepositoryImpl implements BooksRepository {
    @Override
    public List<Book> findByScoresBiggerThen(double goodreadsScoreThreshold, double lubimyczytacScoreThreshold) {
        return mongoOperations.find(
                Query.query(new Criteria().andOperator(
                        Criteria.where("score.goodreads").gt(goodreadsScoreThreshold),
                        Criteria.where("score.lubimyczytac").gt(lubimyczytacScoreThreshold)
                )).with(Sort.by(Sort.Direction.ASC, "score.goodreads", "score.lubimyczytac")),
                Book.class
        );
    }
}
```

</details>

---
layout: center
---

### Test 7 - `shouldGroupBooksByPublisherAndSortedByNameAscending`

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```java

@Repository
class BooksRepositoryImpl implements BooksRepository {
    @Override
    public List<BooksGroupedByPublisher> findAllGroupedByPublisher() {
        return mongoOperations
                .aggregate(Aggregation.newAggregation(
                        Aggregation.group("publisher")
                                .addToSet("$$ROOT").as("books")
                                .count().as("count"),
                        Aggregation.sort(Sort.Direction.ASC, "_id")
                ), Book.class, BooksGroupedByPublisher.class)
                .getMappedResults();
    }
}
```

</details>

---
layout: center
---

### Test 8 - `shouldFindBooksWithKsiążkaWordInDescription`

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```java

@Repository
class BooksRepositoryImpl implements BooksRepository {
    @Override
    public List<Book> findByTextInDescription(String text) {
        return mongoOperations.find(
                TextQuery.queryText(TextCriteria.forDefaultLanguage().matching(text)),
                Book.class
        );
    }
}
```

</details>

---
layout: center
---

# AuthorsRepository

---
layout: center
---

### Test 9 - `shouldCreateReadUpdateDeleteAuthor`

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```java

@Repository
class AuthorsRepositoryImpl implements AuthorsRepository {
    @Override
    public void save(Author author) {
        mongoOperations.save(author);
    }

    @Override
    public Optional<Author> findById(ObjectId authorId) {
        return springAuthorsRepository.findById(authorId);
    }

    @Override
    public void deleteById(ObjectId authorId) {
        springAuthorsRepository.deleteById(authorId);
    }

    @Override
    public void deleteAll() {
        springAuthorsRepository.deleteAll();
    }
}
```

</details>

<br>

##### Pamiętaj o zaimplementowaniu również metody `deleteAll`, która jest używana w metodzie `beforeEach` przygotowującej dane do testów.

---
layout: center
---

### Test 10 - `shouldFindPolishOrNotLivingAuthorsSortedByDateOfBirthAscending`

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```java

@Repository
class AuthorsRepositoryImpl implements AuthorsRepository {
    @Override
    public List<Author> findByNationalityOrLiving(String nationality, boolean living) {
        Criteria dateOfDeathCriteria = living
                ? Criteria.where("dateOfDeath").exists(false)
                : Criteria.where("dateOfDeath").exists(true);
        return mongoOperations.find(
                Query.query(new Criteria().orOperator(
                        Criteria.where("nationality").is(nationality),
                        dateOfDeathCriteria
                )).with(Sort.by(Sort.Direction.ASC, "dateOfBirth")),
                Author.class
        );
    }
}
```

</details>

---
layout: center
---

### Test 11 - `shouldCalculateAuthorAgesSortedByAgeDescending`

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```java

@Repository
class AuthorsRepositoryImpl implements AuthorsRepository {
    @Override
    public List<AuthorWithAge> calculateAuthorAges(Instant now) {
        var ageCalculator = DateOperators.DateDiff
                .diffValueOf(ConditionalOperators.IfNull.ifNull("dateOfDeath").then(now), "year")
                .toDateOf("dateOfBirth");
        return mongoOperations.aggregate(
                Aggregation.newAggregation(
                        Aggregation
                                .project("name", "surname")
                                .and(ageCalculator)
                                .as("age"),
                        Aggregation.sort(Sort.Direction.DESC, "age")
                ),
                Author.class,
                AuthorWithAge.class
        ).getMappedResults();
    }
}
```

</details>

---
layout: center
---

# AuthorWithBooksRepository

---
layout: center
---

### Test 12 - `shouldPrepareViewModelForAuthorWithBooks` cz. 1

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```java

@Repository
public class AuthorsWithBooksRepository {
    public List<AuthorsWithBooksViewModel> prepareAuthorsWithBooksViewModel(Instant now) {
        var joinAuthorsWithBooksStage = LookupOperation.newLookup()
                .from("books")
                .localField("_id")
                .foreignField("authorIds")
                .as("books");
        var calculateBooksCountStage =
                AddFieldsOperation.addField("booksCount")
                        .withValueOf(ArrayOperators.arrayOf("books").length())
                        .build();
        var calculateAgeStage = AddFieldsOperation.addField("age")
                .withValueOf(DateOperators.DateDiff
                        .diffValueOf(ConditionalOperators.IfNull.ifNull("dateOfDeath").then(now), "year")
                        .toDateOf("dateOfBirth"))
                .build();
        // Przygotowanie kolejnych stage'y
        // Wywołanie agregacji
    }
}
```

</details>

---
layout: center
---

### Test 12 - `shouldPrepareViewModelForAuthorWithBooks` cz. 2

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```java

@Repository
public class AuthorsWithBooksRepository {
    public List<AuthorsWithBooksViewModel> prepareAuthorsWithBooksViewModel(Instant now) {
        // Przygotowanie pierwszych stage'y
        var combineNameWithSurnameStage =
                AddFieldsOperation.addField("author")
                        .withValueOf(StringOperators.Concat
                                .valueOf("name")
                                .concat(" ")
                                .concatValueOf("surname"))
                        .build();
        var sortAscendingByAuthorStage = new SortOperation(Sort.by(Sort.Direction.ASC, "author"));
        var projectToFinalViewStage =
                new ProjectionOperation()
                        .andInclude("author", "booksCount", "dateOfBirth", "dateOfDeath", "age", "nationality", "books");
        // Wywołanie agregacji
    }
}
```

</details>

---
layout: center
---

### Test 12 - `shouldPrepareViewModelForAuthorWithBooks` cz. 3

<br>

<details>
  <summary>Rozwiązanie</summary>
<br>

```java

@Repository
public class AuthorsWithBooksRepository {
    public List<AuthorsWithBooksViewModel> prepareAuthorsWithBooksViewModel(Instant now) {
        // Przygotowanie stage'y
        return mongoOperations.aggregate(
                Aggregation.newAggregation(
                        joinAuthorsWithBooksStage,
                        calculateBooksCountStage,
                        calculateAgeStage,
                        combineNameWithSurnameStage,
                        sortAscendingByAuthorStage,
                        projectToFinalViewStage
                ),
                Author.class,
                AuthorsWithBooksViewModel.class
        ).getMappedResults();
    }
}
```

</details>

---

# Koniec zajęć 2