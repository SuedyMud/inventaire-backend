package be.eafcuccle.projint.inventairebackend.repository;

import be.eafcuccle.projint.inventairebackend.model.ZProjet;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ZProjetRepository extends JpaRepository<ZProjet, Integer> {
    @Query("select zp from ZProjet zp")
    Page<ZProjet> findZProjet(Pageable pageable);

    List<ZProjet> findAll();
}
