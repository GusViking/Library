package dk.ek.library.Catalog.Repository;

import dk.ek.library.Catalog.Model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
