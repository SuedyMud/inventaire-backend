package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZUFrascati;
import be.eafcuccle.projint.inventairebackend.repository.ZUFrascatiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/zufrascati")
public class ZUFrascatiController {

    private final Logger logger = LoggerFactory.getLogger(ZUFrascatiController.class);

    private final ZUFrascatiRepository zuFrascatiRepository;

    public ZUFrascatiController(ZUFrascatiRepository zuFrascatiRepository) {
        this.zuFrascatiRepository = zuFrascatiRepository;
    }

    @GetMapping("/{idunite}/frascati")
    public List<ZUFrascati> getFrascatiByUnite(@PathVariable String idunite) {
        return zuFrascatiRepository.findByUniteId(idunite);
    }

    /*@PostMapping("/ajouter")
    public ResponseEntity<?> ajouterZUFrascati(@RequestBody ZUFrascati zuFrascati, UriComponentsBuilder builder) {
        logger.info("Tentative d'ajout d'un nouveau ZUFrascati avec la référence : " + zuFrascati.getRefunite());

        // Votre logique d'ajout ici
        zuFrascatiRepository.save(zuFrascati);

        URI location = builder.path("/api/zufrascati/{id}").buildAndExpand(zuFrascati.getRefunite()).toUri();
        return ResponseEntity.created(location).body(zuFrascati);
    }

    @GetMapping("/liste")
    public Page<ZUFrascati> listeZUFrascati(Pageable pageable) {
        logger.info("Tentative de récupération d'une liste paginée de ZUFrascati.");
        return zuFrascatiRepository.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerZUFrascati(@PathVariable String id) {
        logger.info("Tentative de suppression d'un ZUFrascati avec la référence : " + id);
        zuFrascatiRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZUFrascati> detailZUFrascati(@PathVariable String id) {
        logger.info("Tentative de récupération du détail d'un ZUFrascati avec la référence : " + id);

        Optional<ZUFrascati> optionalZUFrascati = zuFrascatiRepository.findById(id);
        if (optionalZUFrascati.isPresent()) {
            logger.debug("Succès du détail du ZUFrascati avec la référence : " + id);
            return ResponseEntity.ok(optionalZUFrascati.get());
        } else {
            logger.debug("ZUFrascati introuvable avec la référence : " + id);
            return ResponseEntity.notFound().build();
        }
    }

   @PutMapping("/{id}")
    public ResponseEntity<ZUFrascati> modifierZUFrascati(@PathVariable String id, @RequestBody ZUFrascati updatedZUFrascati) {
        logger.info("Tentative de mise à jour d'un ZUFrascati avec la référence : " + id);

        Optional<ZUFrascati> optionalZUFrascati = zuFrascatiRepository.findById(id);
        if (optionalZUFrascati.isPresent()) {
            ZUFrascati existingZUFrascati = optionalZUFrascati.get();

            // Mettre à jour les champs modifiables
            existingZUFrascati.setReffrascati(updatedZUFrascati.getReffrascati());

            zuFrascatiRepository.save(existingZUFrascati);
            logger.debug("Succès de la mise à jour du ZUFrascati");
            return ResponseEntity.ok(existingZUFrascati);
        } else {
            logger.debug("Échec, ZUFrascati introuvable");
            return ResponseEntity.notFound().build();
        }
    }*/
}
