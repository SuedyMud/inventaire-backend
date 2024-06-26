package be.eafcuccle.projint.inventairebackend.repository;


import be.eafcuccle.projint.inventairebackend.model.ZChercheur;
import be.eafcuccle.projint.inventairebackend.model.ZUnite;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ZUniteRepository extends JpaRepository<ZUnite, String> {
    @Query("select zu from ZUnite zu")
    Page<ZUnite> findZUnite(Pageable pageable);

    /*Page<ZUnite> findAll(Pageable pageable);*/

    List<ZUnite> findAll();

    //List<ZUnite> findAllByComposListIsNotNull();

    /*@Query("SELECT zuc.zchercheur FROM ZUCompos zuc WHERE zuc.zunite.idunite = :uniteId")
    List<ZChercheur> findChercheursByUniteId(String uniteId);*/

    //List<ZUnite> findAllByChercheursIsNotNull();

}
