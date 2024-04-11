package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZUFac;
import be.eafcuccle.projint.inventairebackend.repository.ZUFacRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/zufac")
public class ZUFacController {

    private final Logger logger = LoggerFactory.getLogger(ZUFacController.class);

    private final ZUFacRepository zufacRepository;

    public ZUFacController(ZUFacRepository zufacRepository) {
        this.zufacRepository = zufacRepository;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterZUFac(@RequestBody ZUFac zufac, UriComponentsBuilder builder) {
        // Implémentez la logique pour ajouter un nouvel ZUFac
        return ResponseEntity.ok().build(); // Modifier en conséquence
    }

    @GetMapping("/liste")
    public Page<ZUFac> listeZUFac(Pageable pageable) {
        // Implémentez la logique pour récupérer une liste paginée de ZUFac
        return null; // Modifier en conséquence
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerZUFac(@PathVariable String id) {
        // Implémentez la logique pour supprimer un ZUFac
        return ResponseEntity.ok().build(); // Modifier en conséquence
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZUFac> detailZUFac(@PathVariable String id) {
        // Implémentez la logique pour récupérer le détail d'un ZUFac
        return ResponseEntity.ok().build(); // Modifier en conséquence
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZUFac> modifierZUFac(@PathVariable String id, @RequestBody ZUFac updatedZUFac) {
        // Implémentez la logique pour modifier un ZUFac
        return ResponseEntity.ok().build(); // Modifier en conséquence
    }
}
