package be.eafcuccle.projint.inventairebackend.repository;

import be.eafcuccle.projint.inventairebackend.model.ZProjet; // Modification de l'import
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ZProjetRepository extends JpaRepository<ZProjet, Integer> { // Modification du nom de l'interface
    @Query("select zp from ZProjet zp") // Modification de la requête
    Page<ZProjet> findZProjet(Pageable pageable); // Modification du nom de la méthode

    /*
    // Add a new query method to find persons by status
    @Query("SELECT p FROM Personne p WHERE p.status = ?1")
    Page<ZProjet> findByStatus(String status, Pageable pageable);*/

    List<ZProjet> findAll(); // Modification du type de retour
}
