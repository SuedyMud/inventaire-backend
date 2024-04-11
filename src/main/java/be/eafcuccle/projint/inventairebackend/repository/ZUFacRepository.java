package be.eafcuccle.projint.inventairebackend.repository;

import be.eafcuccle.projint.inventairebackend.model.ZUFac;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ZUFacRepository extends JpaRepository<ZUFac, String> {
    @Query("select zu from ZUFac zu")
    Page<ZUFac> findZUFac(Pageable pageable);

    List<ZUFac> findAll();
}
