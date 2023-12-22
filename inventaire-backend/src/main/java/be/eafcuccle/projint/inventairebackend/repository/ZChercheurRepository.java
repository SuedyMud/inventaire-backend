package be.eafcuccle.projint.inventairebackend.repository;

import be.eafcuccle.projint.inventairebackend.model.ZChercheur;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface  ZChercheurRepository extends JpaRepository<ZChercheur, Integer > {
   @Query("select zp from ZChercheur zp")
    Page<ZChercheur> findZChercheur(Pageable pageable);

    /*
    // Add a new query method to find persons by status
    @Query("SELECT p FROM Personne p WHERE p.status = ?1")
    Page<ZChercheur> findByStatus(String status, Pageable pageable);*/

   List<ZChercheur> findAll();
   
}
