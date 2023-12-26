package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZDiscipCref;
import be.eafcuccle.projint.inventairebackend.repository.ZDiscipCrefRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zdiscipcref")
public class ZDiscipCrefController {

    private final Logger logger = LoggerFactory.getLogger(ZDiscipCrefController.class);

    private final ZDiscipCrefRepository zdiscipCrefRepository; // Modification du nom de la variable
    private int currentId = 1; // Initialisation du compteur

    public ZDiscipCrefController(ZDiscipCrefRepository zdiscipCrefRepository) { // Modification du nom du paramètre
        this.zdiscipCrefRepository = zdiscipCrefRepository; // Modification de l'assignation
    }
/*
    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterDiscipCref(@RequestBody ZDiscipCref zDiscipCref, UriComponentsBuilder builder) { // Modification du nom du paramètre
        logger.info("Tentative d'ajout d'une nouvelle DiscipCref avec l'ID : " + zDiscipCref.getIdche()); // Modification du nom de l'objet

        // Affecter un nouvel ID unique en utilisant le compteur
        zDiscipCref.setIdche(currentId++); // Utilisation du compteur et incrémentation
        zDiscipCref.setDDig(new Date()); // Définir la date actuelle

        zDiscipCrefRepository.save(zDiscipCref); // Modification du nom de la variable

        URI localisation = builder.path("/api/zdiscipcref/{id}").buildAndExpand(zDiscipCref.getIdche()).toUri(); // Modification du mapping
        return ResponseEntity.created(localisation).body(zDiscipCref); // Modification du nom de l'objet
    }*/

    @GetMapping("/liste")
    public Page<ZDiscipCref> listeDiscipCref(Pageable pageable) {
        logger.info("Tentative de récupération d'une liste paginée de DiscipCref.");
        return zdiscipCrefRepository.findAll(pageable);
    }

    // ... (autres méthodes)
}
