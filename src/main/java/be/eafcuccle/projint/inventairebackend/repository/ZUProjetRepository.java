package be.eafcuccle.projint.inventairebackend.repository;

import be.eafcuccle.projint.inventairebackend.model.ZProjet;
import be.eafcuccle.projint.inventairebackend.model.ZUFac;
import be.eafcuccle.projint.inventairebackend.model.ZUProjet;
import be.eafcuccle.projint.inventairebackend.model.ZUnite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ZUProjetRepository extends JpaRepository<ZProjet, Integer> {
    @Query("select zp from ZProjet zp")
    Page<ZUProjet> findZProjet(Pageable pageable);

    List<ZProjet> findAll();

   /* @Query("SELECT zup.zunite FROM ZUProjet zup WHERE zup.zprojet.id = ?1")
    List<ZUnite> findUnitesByProjetId(Integer idprojet);

    List<ZUProjet> findByZunite_Idunite(String idunite);*/

}
