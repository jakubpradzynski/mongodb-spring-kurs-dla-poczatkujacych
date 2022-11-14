package pl.jakubpradzynski.mongodb_basics.books;

import java.util.Set;

import static pl.jakubpradzynski.mongodb_basics.authors.SampleAuthors.*;
import static pl.jakubpradzynski.mongodb_basics.books.Genre.INVESTMENT;
import static pl.jakubpradzynski.mongodb_basics.books.Genre.PROGRAMMING;

public class SampleBooks {
    public static Book malaKsiazkaZdroworozsadkowegoInwestowania = Book.builder()
            .generateId()
            .title("Mała książka zdroworozsądkowego inwestowania")
            .description("Jeden z największych bestsellerów dla inwestorów po raz pierwszy w Polsce! John C. Bogle zrewolucjonizował świat inwestowaniem indeksowym a w jego \"Mała książka\" to najważniejsza publikacja dla każdego pasywnego inwestora.")
            .publisher("Milion Kroków")
            .publishYear(2022)
            .numberOfPages(220)
            .isbnNumber("9788395529276")
            .genres(Set.of(INVESTMENT))
            .score(new Score(4.16, 8.3))
            .authorIds(Set.of(johnBogle.id()))
            .build();

    public static Book sladamiWarrenaBuffeta = Book.builder()
            .generateId()
            .title("Śladami Warrena Buffetta")
            .description("\"Śladami Warrena Buffetta\" to ponad 350 stron ciekawych historii, ale przede wszystkim praktycznej wiedzy o inwestowaniu, oszczędzaniu i budowaniu majątku w długim terminie. Ta książka pomoże Ci nauczyć się zasad najwybitniejszego inwestora na świecie.")
            .publisher("Milion Kroków")
            .publishYear(2019)
            .numberOfPages(350)
            .isbnNumber("97883955292207")
            .genres(Set.of(INVESTMENT))
            .score(new Score(4.21, 6.9))
            .authorIds(Set.of(przemyslawGerschmann.id(), tomaszJaroszek.id()))
            .build();

    public static Book zbijFortuneNaDywidendach = Book.builder()
            .generateId()
            .title("Zbij fortunę na dywidendach")
            .description("Inwestowanie w spółki dywidendowe ma na świecie wieloletnią tradycję i stale rosnącą rzeszę fanów. Dziś nic nie stoi na przeszkodzie, aby polski inwestor otworzył się na międzynarodowe rynki inwestycyjne, a ta książka przedstawia mapę prowadzącą przez świat spółek dywidendowych.")
            .publisher("Milion Kroków")
            .publishYear(2021)
            .numberOfPages(255)
            .isbnNumber("9788395529238")
            .genres(Set.of(INVESTMENT))
            .score(new Score(3.89, 7.3))
            .authorIds(Set.of(marcLichtenfeld.id()))
            .build();

    public static Book najbogatszyCzlowiekWBabilonie = Book.builder()
            .generateId()
            .title("Najbogatszy człowiek w Babilonie")
            .description("\"Najbogatszy człowiek w Babilonie\" nie dostarcza szybkich rozwiązań, ale znakomicie radzi, jak spłacać długi, zapewnić sobie stały dochód i w rezultacie budować majątek.")
            .publisher("Studio EMKA")
            .publishYear(2017)
            .numberOfPages(176)
            .isbnNumber("9788365068293")
            .genres(Set.of(INVESTMENT))
            .score(new Score(4.26, 7.4))
            .authorIds(Set.of(georgeClason.id()))
            .build();

    public static Book inteligentnyInwestor = Book.builder()
            .generateId()
            .title("Inteligentny inwestor. Najlepsza książka o inwestowaniu wartościowym")
            .description("Najznakomitszy doradca inwestycyjny dwudziestego wieku, Benjamin Graham, uczył i inspirował ludzi na całym świecie. Jego filozofia „inwestowania wartościowego” – które chroni inwestorów przed podstawowymi błędami i uczy ich stosowania długofalowych strategii – od chwili pierwszego wydania uczyniła z Inteligentnego inwestora prawdziwą biblię giełdy papierów wartościowych. Rozwój giełdy przez wiele lat potwierdzał mądrość strategii Grahama.")
            .publisher("Studio EMKA")
            .publishYear(2006)
            .numberOfPages(536)
            .isbnNumber("9788388931895")
            .genres(Set.of(INVESTMENT))
            .score(new Score(4.25, 7.7))
            .authorIds(Set.of(benjaminGraham.id()))
            .build();

    public static Book czystyKod = Book.builder()
            .generateId()
            .title("Czysty kod. Podręcznik dobrego programisty")
            .description("O tym, ile problemów sprawia niedbale napisany kod, wie każdy programista. Nie wszyscy jednak wiedzą, jak napisać ten świetny, „czysty” kod i czym właściwie powinien się on charakteryzować. Co więcej – jak odróżnić dobry kod od złego? Odpowiedź na te pytania oraz sposoby tworzenia czystego, czytelnego kodu znajdziesz właśnie w tej książce. Podręcznik jest obowiązkową pozycją dla każdego, kto chce poznać techniki rzetelnego i efektywnego programowania.")
            .publisher("Helion")
            .publishYear(2010)
            .numberOfPages(424)
            .isbnNumber("9788328302341")
            .genres(Set.of(PROGRAMMING))
            .score(new Score(4.39, 8.0))
            .authorIds(Set.of(robertMartin.id()))
            .build();

    public static Book wzorceProjektowe = Book.builder()
            .generateId()
            .title("Wzorce projektowe. Elementy oprogramowania obiektowego wielokrotnego użytku")
            .description("Projektowanie oprogramowania obiektowego nie jest łatwe, a przy założeniu, że powinno ono nadawać się do wielokrotnego użytku, staje się naprawdę skomplikowane. Aby stworzyć dobry projekt, najlepiej skorzystać ze sprawdzonych i efektywnych rozwiązań, które wcześniej były już stosowane. W tej książce znajdziesz właśnie najlepsze doświadczenia z obszaru programowania obiektowego, zapisane w formie wzorców projektowych gotowych do natychmiastowego użycia!")
            .publisher("Helion")
            .publishYear(2010)
            .numberOfPages(376)
            .isbnNumber("9788328386099")
            .genres(Set.of(PROGRAMMING))
            .score(new Score(4.19, 7.3))
            .authorIds(Set.of(erichGamma.id(), richardHelm.id(), ralphJohnson.id(), johnVlissides.id()))
            .build();

    public static Book mistrzCzystegoKodu = Book.builder()
            .generateId()
            .title("Mistrz czystego kodu. Kodeks postępowania profesjonalnych programistów")
            .description("W trakcie lektury dowiesz się, jakie cechy charakteryzują profesjonalnego programistę, a jest ich sporo! W pierwszej kolejności musisz nauczyć się mówić „nie”. Są też sytuacje, kiedy trzeba powiedzieć „tak” — dowiesz się, kiedy i jak to robić. Ponadto poznasz najlepsze techniki zarządzania czasem oraz przekonasz się, jak presja, zmęczenie i pośpiech wpływają na jakość Twojego kodu. W kolejnych rozdziałach Robert C. Martin zapozna Cię z różnymi sposobami podejścia do testowania kodu oraz współpracy między programistami a innymi ludźmi.")
            .publisher("Helion")
            .publishYear(2010)
            .numberOfPages(216)
            .isbnNumber("9788328382961")
            .genres(Set.of(PROGRAMMING))
            .score(new Score(4.28, 7.4))
            .authorIds(Set.of(robertMartin.id()))
            .build();

    public static void saveAllBooks(BooksRepository booksRepository) {
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
