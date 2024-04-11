package be.eafcuccle.projint.inventairebackend.repository;

import be.eafcuccle.projint.inventairebackend.model.ZUFrascati;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ZUFrascatiRepository extends JpaRepository<ZUFrascati, String> {
    @Query("select zu from ZUFrascati zu")
    Page<ZUFrascati> findZUFrascati(Pageable pageable);

    List<ZUFrascati> findAll();
}
