package be.eafcuccle.projint.inventairebackend.repository;

import be.eafcuccle.projint.inventairebackend.model.ZSFI; // Modification de l'import
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ZSFIRepository extends JpaRepository<ZSFI, Integer> { // Modification du nom de l'interface
    @Query("select zs from ZSFI zs") // Modification de la requête
    Page<ZSFI> findZSFI(Pageable pageable); // Modification du nom de la méthode

    /*
    // Add a new query method to find persons by status
    @Query("SELECT p FROM Personne p WHERE p.status = ?1")
    Page<ZSFI> findByStatus(String status, Pageable pageable);*/

    List<ZSFI> findAll(); // Modification du type de retour
}
