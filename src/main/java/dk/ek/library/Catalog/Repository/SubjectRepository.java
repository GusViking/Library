package dk.ek.library.Catalog.Repository;

import dk.ek.library.Catalog.Model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
