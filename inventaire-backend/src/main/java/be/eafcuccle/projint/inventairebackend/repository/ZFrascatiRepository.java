package be.eafcuccle.projint.inventairebackend.repository;

import be.eafcuccle.projint.inventairebackend.model.ZFrascati; // Modification de l'import
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ZFrascatiRepository extends JpaRepository<ZFrascati, String> { // Modification du nom de l'interface
    @Query("select zf from ZFrascati zf") // Modification de la requête
    Page<ZFrascati> findZFrascati(Pageable pageable); // Modification du nom de la méthode

    /*
    // Add a new query method to find persons by status
    @Query("SELECT p FROM Personne p WHERE p.status = ?1")
    Page<ZFrascati> findByStatus(String status, Pageable pageable);*/

    List<ZFrascati> findAll(); // Modification du type de retour
}
