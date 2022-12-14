package pl.jakubpradzynski.mongodb_basics.authors;

import org.assertj.core.util.DateUtil;

public class SampleAuthors {
    public static Author johnBogle = Author.builder()
            .generateId()
            .name("John C.")
            .surname("Bogle")
            .nationality("American")
            .dateOfBirth(DateUtil.parse("1952-12-05"))
            .build();

    public static Author przemyslawGerschmann = Author.builder()
            .generateId()
            .name("Przemysław")
            .surname("Gerschmann")
            .nationality("Polish")
            .dateOfBirth(DateUtil.parse("1984-02-02")) // random date
            .build();

    public static Author tomaszJaroszek = Author.builder()
            .generateId()
            .name("Tomasz")
            .surname("Jaroszek")
            .nationality("Polish")
            .dateOfBirth(DateUtil.parse("1985-01-01")) // random date
            .build();

    public static Author marcLichtenfeld = Author.builder()
            .generateId()
            .name("Marc")
            .surname("Lichtenfeld")
            .nationality("American")
            .dateOfBirth(DateUtil.parse("1973-05-12")) // random date
            .build();

    public static Author georgeClason = Author.builder()
            .generateId()
            .name("George C.")
            .surname("Clason")
            .nationality("American")
            .dateOfBirth(DateUtil.parse("1874-11-07"))
            .dateOfDeath(DateUtil.parse("1957-04-05"))
            .build();

    public static Author benjaminGraham = Author.builder()
            .generateId()
            .name("Benjamin")
            .surname("Graham")
            .nationality("American")
            .dateOfBirth(DateUtil.parse("1894-05-09"))
            .dateOfDeath(DateUtil.parse("1976-09-21"))
            .build();

    public static Author robertMartin = Author.builder()
            .generateId()
            .name("Robert C.")
            .surname("Martin")
            .nationality("American")
            .dateOfBirth(DateUtil.parse("1952-12-05"))
            .build();

    public static Author erichGamma = Author.builder()
            .generateId()
            .name("Erich")
            .surname("Gamma")
            .nationality("American")
            .dateOfBirth(DateUtil.parse("1961-03-13"))
            .build();

    public static Author richardHelm = Author.builder()
            .generateId()
            .name("Richard")
            .surname("Helm")
            .nationality("American")
            .dateOfBirth(DateUtil.parse("1960-10-03")) // random date
            .build();

    public static Author ralphJohnson = Author.builder()
            .generateId()
            .name("Ralph")
            .surname("Johnson")
            .nationality("American")
            .dateOfBirth(DateUtil.parse("1955-10-07"))
            .build();

    public static Author johnVlissides = Author.builder()
            .generateId()
            .name("John")
            .surname("Vlissides")
            .nationality("American")
            .dateOfBirth(DateUtil.parse("1961-08-02"))
            .dateOfDeath(DateUtil.parse("2005-11-24"))
            .build();

    public static void saveAllAuthors(AuthorsRepository authorsRepository) {
        authorsRepository.save(johnBogle);
        authorsRepository.save(przemyslawGerschmann);
        authorsRepository.save(tomaszJaroszek);
        authorsRepository.save(marcLichtenfeld);
        authorsRepository.save(georgeClason);
        authorsRepository.save(benjaminGraham);
        authorsRepository.save(robertMartin);
        authorsRepository.save(erichGamma);
        authorsRepository.save(richardHelm);
        authorsRepository.save(ralphJohnson);
        authorsRepository.save(johnVlissides);
    }
}
