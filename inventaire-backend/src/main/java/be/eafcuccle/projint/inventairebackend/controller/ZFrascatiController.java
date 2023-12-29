package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZFrascati; // Modification de l'import
import be.eafcuccle.projint.inventairebackend.repository.ZFrascatiRepository; // Modification de l'import
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
@RequestMapping("/api/zfrascati") // Modification du mapping
public class ZFrascatiController {

    private final Logger logger = LoggerFactory.getLogger(ZFrascatiController.class);

    private final ZFrascatiRepository zfrascatiRepository; // Modification du nom de la variable
    private int currentId = 1; // Initialisation du compteur

    public ZFrascatiController(ZFrascatiRepository zfrascatiRepository) { // Modification du nom du paramètre
        this.zfrascatiRepository = zfrascatiRepository; // Modification de l'assignation
    }
    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterFrascati(@RequestBody ZFrascati zfrascati, UriComponentsBuilder builder) {
        logger.info("Tentative d'ajout d'un nouveau ZFrascati avec l'ID : " + zfrascati.getIdfrascati());

        // Affecter un nouvel ID unique en utilisant UUID
        zfrascati.setIdfrascati(String.valueOf(currentId++)); // Conversion de l'ID en chaîne de caractères et incrémentation

        zfrascatiRepository.save(zfrascati);

        URI localisation = builder.path("/api/zfrascati/{id}").buildAndExpand(zfrascati.getIdfrascati()).toUri();
        return ResponseEntity.created(localisation).body(zfrascati);
    }

    @GetMapping("/liste")
    public Page<ZFrascati> listeFrascati(Pageable pageable) { // Modification du nom de la méthode et du type de retour
        logger.info("Tentative de récupération d'une liste paginée de Frascati."); // Modification du nom de l'objet
        return zfrascatiRepository.findAll(pageable); // Modification du nom de la variable
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerFrascati(@PathVariable String id) {
        logger.info("Tentative de suppression d'un Frascati avec l'ID : " + id);
        zfrascatiRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ZFrascati> modifierFrascati(@PathVariable String id, @RequestBody ZFrascati updatedFrascati) {
        logger.info("Tentative de mise à jour d'un Frascati avec l'ID : " + id);

        Optional<ZFrascati> optionalFrascati = zfrascatiRepository.findById(id);
        if (optionalFrascati.isPresent()) {
            ZFrascati existingFrascati = optionalFrascati.get();

            // Mettre à jour les champs modifiables
            existingFrascati.setFrascati(updatedFrascati.getFrascati());
            existingFrascati.setFrascatiUK(updatedFrascati.getFrascatiUK());
            existingFrascati.setDescription(updatedFrascati.getDescription());
            existingFrascati.setDescriptionUK(updatedFrascati.getDescriptionUK());
            existingFrascati.setRefgrdiscip(updatedFrascati.getRefgrdiscip());
            existingFrascati.setOrdre(updatedFrascati.getOrdre());


            zfrascatiRepository.save(existingFrascati);
            logger.debug("Succès de la mise à jour du Frascati");
            return ResponseEntity.ok(existingFrascati);
        } else {
            logger.debug("Échec, Frascati introuvable");
            return ResponseEntity.notFound().build();
        }
    }
}
