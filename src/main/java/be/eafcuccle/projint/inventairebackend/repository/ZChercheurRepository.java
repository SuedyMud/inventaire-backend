package be.eafcuccle.projint.inventairebackend.repository;

import be.eafcuccle.projint.inventairebackend.model.ZChercheur;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface  ZChercheurRepository extends JpaRepository<ZChercheur, Integer > {
   @Query("select zc from ZChercheur zc")
    Page<ZChercheur> findZChercheur(Pageable pageable);

   List<ZChercheur> findAll();
   
}
