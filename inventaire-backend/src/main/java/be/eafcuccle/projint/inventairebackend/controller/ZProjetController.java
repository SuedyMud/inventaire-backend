package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZProjet; // Modification de l'import
import be.eafcuccle.projint.inventairebackend.repository.ZProjetRepository; // Modification de l'import
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;

@RestController
@RequestMapping("/api/zprojet") // Modification du mapping
public class ZProjetController {

    private final Logger logger = LoggerFactory.getLogger(ZProjetController.class);

    private final ZProjetRepository zprojetRepository; // Modification du nom de la variable
    private int currentId = 1; // Initialisation du compteur

    public ZProjetController(ZProjetRepository zprojetRepository) { // Modification du nom du paramètre
        this.zprojetRepository = zprojetRepository; // Modification de l'assignation
    }
/*
    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterProjet(@RequestBody ZProjet zProjet, UriComponentsBuilder builder) { // Modification du nom du paramètre
        logger.info("Tentative d'ajout d'un nouveau Projet avec l'ID : " + zProjet.getIdche()); // Modification du nom de l'objet

        // Affecter un nouvel ID unique en utilisant le compteur
        zProjet.setIdche(currentId++); // Utilisation du compteur et incrémentation
        zProjet.setDDig(new Date()); // Définir la date actuelle

        zprojetRepository.save(zProjet); // Modification du nom de la variable

        URI localisation = builder.path("/api/zprojet/{id}").buildAndExpand(zProjet.getIdche()).toUri(); // Modification du mapping
        return ResponseEntity.created(localisation).body(zProjet); // Modification du nom de l'objet
    }*/

    @GetMapping("/liste")
    public Page<ZProjet> listeProjets(Pageable pageable) { // Modification du nom de la méthode et du type de retour
        logger.info("Tentative de récupération d'une liste paginée de Projets."); // Modification du nom de l'objet
        return zprojetRepository.findAll(pageable); // Modification du nom de la variable
    }

    // ... (autres méthodes)
}
