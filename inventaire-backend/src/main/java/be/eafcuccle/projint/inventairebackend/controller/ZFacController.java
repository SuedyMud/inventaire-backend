package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZFac; // Modification de l'import
import be.eafcuccle.projint.inventairebackend.repository.ZFacRepository; // Modification de l'import
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/api/zfac") // Modification du mapping
public class ZFacController {

    private final Logger logger = LoggerFactory.getLogger(ZFacController.class);

    private final ZFacRepository zfacRepository; // Modification du nom de la variable
    private int currentId = 1; // Initialisation du compteur

    public ZFacController(ZFacRepository zfacRepository) { // Modification du nom du paramètre
        this.zfacRepository = zfacRepository; // Modification de l'assignation
    }

    /*
    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterFac(@RequestBody ZFac zFac, UriComponentsBuilder builder) { // Modification du nom du paramètre
        logger.info("Tentative d'ajout d'une nouvelle Fac avec l'ID : " + zFac.getIdche()); // Modification du nom de l'objet

        // Affecter un nouvel ID unique en utilisant le compteur
        zFac.setIdche(currentId++); // Utilisation du compteur et incrémentation
        zFac.setDDig(new Date()); // Définir la date actuelle

        zFacRepository.save(zFac); // Modification du nom de la variable

        URI localisation = builder.path("/api/zfac/{id}").buildAndExpand(zFac.getIdche()).toUri(); // Modification du mapping
        return ResponseEntity.created(localisation).body(zFac); // Modification du nom de l'objet
    }*/

    @GetMapping("/liste")
    public Page<ZFac> listeFac(Pageable pageable) { // Modification du nom de la méthode et du type de retour
        logger.info("Tentative de récupération d'une liste paginée de Facs."); // Modification du nom de l'objet
        return zfacRepository.findAll(pageable); // Modification du nom de la variable
    }

    // ... (autres méthodes)
}
