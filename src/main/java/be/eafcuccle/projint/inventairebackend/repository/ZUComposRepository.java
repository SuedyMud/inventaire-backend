package be.eafcuccle.projint.inventairebackend.repository;

import be.eafcuccle.projint.inventairebackend.model.ZUCompos;
import be.eafcuccle.projint.inventairebackend.model.ZUnite;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ZUComposRepository extends JpaRepository<ZUCompos, String> {
    @Query("select zu from ZUCompos zu")
    Page<ZUCompos> findZUCompos(Pageable pageable);

    List<ZUCompos> findAll();

    // Nouvelle méthode pour récupérer les responsables d'une unité
    List<ZUCompos> findByZunite_IduniteAndResponsable(String idunite, String responsable);
    ZUCompos findByIdAndResponsable(String id, String responsable);

    List<ZUCompos> findByZunite(ZUnite zunite);


}
