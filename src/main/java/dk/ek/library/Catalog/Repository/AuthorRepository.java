package dk.ek.library.Catalog.Repository;

import dk.ek.library.Catalog.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
