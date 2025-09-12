package dk.ek.library.Common;

import dk.ek.library.Catalog.Model.*;
import dk.ek.library.Catalog.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static dk.ek.library.Catalog.Model.WorkType.BOOK;

@Component
public class InitData implements CommandLineRunner {

    private final WorkRepository workRepository;
    private final EditionRepository editionRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final SubjectRepository subjectRepository;

    public InitData(WorkRepository workRepository,
                    EditionRepository editionRepository,
                    PublisherRepository publisherRepository,
                    AuthorRepository authorRepository,
                    SubjectRepository subjectRepository) {
        this.workRepository = workRepository;
        this.editionRepository = editionRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void run(String... args) {
        // --- Publishers ---
        Publisher publisher1 = new Publisher();
        publisher1.setName("HarperCollins");
        publisher1.setAddress("195 Broadway, New York, NY");
        publisher1.setContactInfo("contact@harpercollins.com");

        Publisher publisher2 = new Publisher();
        publisher2.setName("Allen & Unwin");
        publisher2.setAddress("83 Alexander St, Crows Nest, Australia");
        publisher2.setContactInfo("info@allenandunwin.com");

        publisherRepository.saveAll(List.of(publisher1, publisher2));

        // --- Authors ---
        Author author1 = new Author();
        author1.setName("J.R.R. Tolkien");

        authorRepository.save(author1);

        // --- Subjects ---
        Subject subject1 = new Subject();
        subject1.setName("Fantasy");

        subjectRepository.save(subject1);

        // --- Works ---
        Work work1 = new Work();
        work1.setTitle("Lord of the Rings: Fellowship of the Ring");
        work1.setWorkType(BOOK);
        work1.setDetails("Introduction to the universe of J.R.R. Tolkien");
        work1.setAuthors(Set.of(author1));
        work1.setSubjects(Set.of(subject1));

        Work work2 = new Work();
        work2.setTitle("Lord of the Rings: Two Towers");
        work2.setWorkType(BOOK);
        work2.setDetails("The Middle of the Adventure");
        work2.setAuthors(Set.of(author1));
        work2.setSubjects(Set.of(subject1));

        Work work3 = new Work();
        work3.setTitle("Lord of the Rings: Return of the King");
        work3.setWorkType(BOOK);
        work3.setDetails("The End of an Era");
        work3.setAuthors(Set.of(author1));
        work3.setSubjects(Set.of(subject1));

        workRepository.saveAll(List.of(work1, work2, work3));

        // --- Editions ---
        Edition edition1 = new Edition();
        edition1.setEditNumber("1st");
        edition1.setYear(1954);
        edition1.setFormat("Hardcover");
        edition1.setPublisher(publisher1);
        edition1.setWork(work1);

        Edition edition2 = new Edition();
        edition2.setEditNumber("2nd");
        edition2.setYear(1965);
        edition2.setFormat("Paperback");
        edition2.setPublisher(publisher2);
        edition2.setWork(work2);

        editionRepository.saveAll(List.of(edition1, edition2));

        // Link editions to works (keep bidirectional relationship consistent)
        work1.setEditions(Set.of(edition1));
        work2.setEditions(Set.of(edition2));
        workRepository.saveAll(List.of(work1, work2));

        System.out.println("Works, Editions, Authors, Subjects, and Publishers inserted into database!");
    }
}
