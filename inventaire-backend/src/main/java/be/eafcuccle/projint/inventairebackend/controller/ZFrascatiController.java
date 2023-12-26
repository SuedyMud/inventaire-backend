package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZFrascati; // Modification de l'import
import be.eafcuccle.projint.inventairebackend.repository.ZFrascatiRepository; // Modification de l'import
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zfrascati") // Modification du mapping
public class ZFrascatiController {

    private final Logger logger = LoggerFactory.getLogger(ZFrascatiController.class);

    private final ZFrascatiRepository zfrascatiRepository; // Modification du nom de la variable
    private int currentId = 1; // Initialisation du compteur

    public ZFrascatiController(ZFrascatiRepository zfrascatiRepository) { // Modification du nom du paramètre
        this.zfrascatiRepository = zfrascatiRepository; // Modification de l'assignation
    }
/*
    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterFrascati(@RequestBody ZFrascati zFrascati, UriComponentsBuilder builder) { // Modification du nom du paramètre
        logger.info("Tentative d'ajout d'un nouveau Frascati avec l'ID : " + zFrascati.getIdche()); // Modification du nom de l'objet

        // Affecter un nouvel ID unique en utilisant le compteur
        zFrascati.setIdche(currentId++); // Utilisation du compteur et incrémentation
        zFrascati.setDDig(new Date()); // Définir la date actuelle

        zFrascatiRepository.save(zFrascati); // Modification du nom de la variable

        URI localisation = builder.path("/api/zfrascati/{id}").buildAndExpand(zFrascati.getIdche()).toUri(); // Modification du mapping
        return ResponseEntity.created(localisation).body(zFrascati); // Modification du nom de l'objet
    }
*/
    @GetMapping("/liste")
    public Page<ZFrascati> listeFrascati(Pageable pageable) { // Modification du nom de la méthode et du type de retour
        logger.info("Tentative de récupération d'une liste paginée de Frascati."); // Modification du nom de l'objet
        return zfrascatiRepository.findAll(pageable); // Modification du nom de la variable
    }

    // ... (autres méthodes)
}
