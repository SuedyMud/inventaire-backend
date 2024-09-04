package be.eafcuccle.projint.inventairebackend.repository;

import be.eafcuccle.projint.inventairebackend.model.ZUFrascati;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ZUFrascatiRepository extends JpaRepository<ZUFrascati, String> {

  /*  @Query("SELECT zuf FROM ZUFrascati zuf WHERE zuf.zunite.idunite = :idunite")
    List<ZUFrascati> findByUniteId(@Param("idunite") String idunite);*/

    Page<ZUFrascati> findAll(Pageable pageable);

    List<ZUFrascati> findByZunite_Idunite(String idunite);
}
