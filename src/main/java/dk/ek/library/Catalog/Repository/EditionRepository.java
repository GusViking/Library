package dk.ek.library.Catalog.Repository;

import dk.ek.library.Catalog.Model.Edition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditionRepository extends JpaRepository<Edition, Long> {
}
