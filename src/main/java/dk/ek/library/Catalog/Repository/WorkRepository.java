package dk.ek.library.Catalog.Repository;

import dk.ek.library.Catalog.Model.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {

    List<Work> findByTitleContaining(String title);


}
