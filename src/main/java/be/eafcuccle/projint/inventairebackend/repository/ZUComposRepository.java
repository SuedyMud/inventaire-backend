package be.eafcuccle.projint.inventairebackend.repository;

import be.eafcuccle.projint.inventairebackend.model.ZUCompos;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ZUComposRepository extends JpaRepository<ZUCompos, String> {
    @Query("select zu from ZUCompos zu")
    Page<ZUCompos> findZUCompos(Pageable pageable);

    List<ZUCompos> findAll();
}
