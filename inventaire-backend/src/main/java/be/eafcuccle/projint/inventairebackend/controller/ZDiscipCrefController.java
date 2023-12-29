package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZDiscipCref;
import be.eafcuccle.projint.inventairebackend.repository.ZDiscipCrefRepository;
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
@RequestMapping("/api/zdiscipcref")
public class ZDiscipCrefController {

    private final Logger logger = LoggerFactory.getLogger(ZDiscipCrefController.class);

    private final ZDiscipCrefRepository zdiscipCrefRepository; // Modification du nom de la variable
    private int currentId = 1; // Initialisation du compteur

    public ZDiscipCrefController(ZDiscipCrefRepository zdiscipCrefRepository) { // Modification du nom du paramètre
        this.zdiscipCrefRepository = zdiscipCrefRepository; // Modification de l'assignation
    }
    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterZDiscipCref(@RequestBody ZDiscipCref zdiscipCref, UriComponentsBuilder builder) {
        logger.info("Tentative d'ajout d'une nouvelle ZDiscipCref avec l'ID : " + zdiscipCref.getIdcodecref());

        // Affecter un nouvel ID unique en utilisant UUID
        zdiscipCref.setIdcodecref(String.valueOf(currentId++)); // Conversion de l'ID en chaîne de caractères et incrémentation


        zdiscipCrefRepository.save(zdiscipCref);

        URI localisation = builder.path("/api/zdiscipcref/{id}").buildAndExpand(zdiscipCref.getIdcodecref()).toUri();
        return ResponseEntity.created(localisation).body(zdiscipCref);
    }

    @GetMapping("/liste")
    public Page<ZDiscipCref> listeZDiscipCref(Pageable pageable) {
        logger.info("Tentative de récupération d'une liste paginée de ZDiscipCref.");
        return zdiscipCrefRepository.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerZDiscipCref(@PathVariable String id) {
        logger.info("Tentative de suppression d'une ZDiscipCref avec l'ID : " + id);
        zdiscipCrefRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZDiscipCref> modifierZDiscipCref(@PathVariable String id, @RequestBody ZDiscipCref updatedDiscipCref) {
        logger.info("Tentative de mise à jour d'une DiscipCref avec l'ID : " + id);

        Optional<ZDiscipCref> optionalDiscipCref = zdiscipCrefRepository.findById(id);
        if (optionalDiscipCref.isPresent()) {
            ZDiscipCref existingDiscipCref = optionalDiscipCref.get();

            // Mettre à jour les champs modifiables
            existingDiscipCref.setDiscipline(updatedDiscipCref.getDiscipline());
            existingDiscipCref.setDisciplineUK(updatedDiscipCref.getDisciplineUK());


            zdiscipCrefRepository.save(existingDiscipCref);
            logger.debug("Succès de la mise à jour de la DiscipCref");
            return ResponseEntity.ok(existingDiscipCref);
        } else {
            logger.debug("Échec, DiscipCref introuvable");
            return ResponseEntity.notFound().build();
        }
    }

}
