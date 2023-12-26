package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZSFI; // Modification de l'import
import be.eafcuccle.projint.inventairebackend.repository.ZSFIRepository; // Modification de l'import
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/zsfi") // Modification du mapping
public class ZSFIController {

    private final Logger logger = LoggerFactory.getLogger(ZSFIController.class);

    private final ZSFIRepository zsfiRepository; // Modification du nom de la variable
    private int currentId = 1; // Initialisation du compteur

    public ZSFIController(ZSFIRepository zsfiRepository) { // Modification du nom du paramètre
        this.zsfiRepository = zsfiRepository; // Modification de l'assignation
    }
/*
    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterSFI(@RequestBody ZSFI zsfi, UriComponentsBuilder builder) { // Modification du nom du paramètre
        logger.info("Tentative d'ajout d'un nouveau SFI avec l'ID : " + zsfi.getIdche()); // Modification du nom de l'objet

        // Affecter un nouvel ID unique en utilisant le compteur
        zsfi.setIdche(currentId++); // Utilisation du compteur et incrémentation
        zsfi.setDDig(new Date()); // Définir la date actuelle

        zsfiRepository.save(zsfi); // Modification du nom de la variable

        URI localisation = builder.path("/api/zsfi/{id}").buildAndExpand(zsfi.getIdche()).toUri(); // Modification du mapping
        return ResponseEntity.created(localisation).body(zsfi); // Modification du nom de l'objet
    }*/

    @GetMapping("/liste")
    public Page<ZSFI> listeSFI(Pageable pageable) { // Modification du nom de la méthode et du type de retour
        logger.info("Tentative de récupération d'une liste paginée de SFI."); // Modification du nom de l'objet
        return zsfiRepository.findAll(pageable); // Modification du nom de la variable
    }

    // ... (autres méthodes)
}
