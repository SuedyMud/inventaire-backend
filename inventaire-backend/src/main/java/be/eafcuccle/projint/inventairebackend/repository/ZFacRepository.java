package be.eafcuccle.projint.inventairebackend.repository;

import be.eafcuccle.projint.inventairebackend.model.ZChercheur;
import be.eafcuccle.projint.inventairebackend.model.ZFac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface  ZFacRepository extends JpaRepository<ZFac, String> {
    @Query("select zf from ZFac zf")
    Page<ZFac> findZFac(Pageable pageable);

    /*
    // Add a new query method to find persons by status
    @Query("SELECT p FROM Personne p WHERE p.status = ?1")
    Page<ZChercheur> findByStatus(String status, Pageable pageable);*/

    List<ZFac> findAll();

}