package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZUCompos;
import be.eafcuccle.projint.inventairebackend.repository.ZUComposRepository;
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
@RestController
@RequestMapping("/api/zucompos")
public class ZUComposController {

    private final Logger logger = LoggerFactory.getLogger(ZUComposController.class);

    private final ZUComposRepository zuComposRepository;
    private int currentId = 1; // Initialisation du compteur

    public ZUComposController(ZUComposRepository zuComposRepository) {
        this.zuComposRepository = zuComposRepository;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterZUCompos(@RequestBody ZUCompos zuCompos, UriComponentsBuilder builder) {
        logger.info("Tentative d'ajout d'un nouveau ZUCompos avec la référence : " + zuCompos.getRefunite());

        // Affecter un nouvel ID unique en utilisant le compteur
        // Note : Votre entité ZUCompos n'a pas de champ ID, si vous en avez besoin, assurez-vous de l'ajouter et de le gérer correctement.
        // Je vais supposer que refunite est unique pour identifier l'entité.
        // zuCompos.setId(currentId++); // Utilisation du compteur et incrémentation
        zuCompos.setDatedebut(new Date()); // Définir la date actuelle

        zuComposRepository.save(zuCompos);

        URI localisation = builder.path("/api/zucompos/{id}").buildAndExpand(zuCompos.getRefunite()).toUri();
        return ResponseEntity.created(localisation).body(zuCompos);
    }

    @GetMapping("/liste")
    public Page<ZUCompos> listeZUCompos(Pageable pageable) {
        logger.info("Tentative de récupération d'une liste paginée de ZUCompos.");
        return zuComposRepository.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerZUCompos(@PathVariable String id) {
        logger.info("Tentative de suppression d'un ZUCompos avec la référence : " + id);
        zuComposRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ZUCompos> detailZUCompos(@PathVariable String id) {
        logger.info("Tentative de récupération du détail d'un ZUCompos avec la référence : " + id);

        Optional<ZUCompos> optionalZUCompos = zuComposRepository.findById(id);
        if (optionalZUCompos.isPresent()) {
            logger.debug("Succès du détail du ZUCompos avec la référence : " + id);
            return ResponseEntity.ok(optionalZUCompos.get());
        } else {
            logger.debug("ZUCompos introuvable avec la référence : " + id);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZUCompos> modifierZUCompos(@PathVariable String id, @RequestBody ZUCompos updatedZUCompos) {
        logger.info("Tentative de mise à jour d'un ZUCompos avec la référence : " + id);

        Optional<ZUCompos> optionalZUCompos = zuComposRepository.findById(id);
        if (optionalZUCompos.isPresent()) {
            ZUCompos existingCompos = optionalZUCompos.get();

            // Mettre à jour les champs modifiables
            existingCompos.setRefche(updatedZUCompos.getRefche());
            existingCompos.setResponsable(updatedZUCompos.getResponsable());
            existingCompos.setDatedebut(updatedZUCompos.getDatedebut());
            existingCompos.setDatefin(updatedZUCompos.getDatefin());
            existingCompos.setOrdre(updatedZUCompos.getOrdre());

            zuComposRepository.save(existingCompos);
            logger.debug("Succès de la mise à jour du ZUCompos");
            return ResponseEntity.ok(existingCompos);
        } else {
            logger.debug("Échec, ZUCompos introuvable");
            return ResponseEntity.notFound().build();
        }
    }
}
