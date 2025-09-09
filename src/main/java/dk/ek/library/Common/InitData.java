package dk.ek.library.Common;

import dk.ek.library.Catalog.Model.*;
import dk.ek.library.Catalog.Repository.EditionRepository;
import dk.ek.library.Catalog.Repository.PublisherRepository;
import dk.ek.library.Catalog.Repository.WorkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static dk.ek.library.Catalog.Model.WorkType.BOOK;
import java.util.List;

@Component
public class InitData implements CommandLineRunner {

    private final WorkRepository workRepository;
    private final EditionRepository editionRepository;
    private final PublisherRepository publisherRepository;

    public InitData(WorkRepository workRepository,
                    EditionRepository editionRepository,
                    PublisherRepository publisherRepository) {
        this.workRepository = workRepository;
        this.editionRepository = editionRepository;
        this.publisherRepository = publisherRepository;
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

        // --- Works ---
        Work work1 = new Work();
        work1.setTitle("Lord of the Rings: Fellowship of the Ring");
        work1.setWorkType(BOOK);
        work1.setDetails("Introduction to the universe of J.R.R. Tolkien");
        work1.setAuthors("J.R.R. Tolkien");
        work1.setSubjects("Fantasy");

        Work work2 = new Work();
        work2.setTitle("Lord of the Rings: Two Towers");
        work2.setWorkType(BOOK);
        work2.setDetails("The Middle of the Adventure");
        work2.setAuthors("J.R.R. Tolkien");
        work2.setSubjects("Fantasy");

        Work work3 = new Work();
        work3.setTitle("Lord of the Rings: Return of the King");
        work3.setWorkType(BOOK);
        work3.setDetails("The End of an Era");
        work3.setAuthors("J.R.R. Tolkien");
        work3.setSubjects("Fantasy");

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
        work1.setEditions(List.of(edition1));
        work2.setEditions(List.of(edition2));
        workRepository.saveAll(List.of(work1, work2));

        System.out.println("Works, Editions, and Publishers inserted into database!");
    }
}
