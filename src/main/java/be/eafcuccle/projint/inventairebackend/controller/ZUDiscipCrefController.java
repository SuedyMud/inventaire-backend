package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZUDiscipCref;
import be.eafcuccle.projint.inventairebackend.repository.ZUDiscipCrefRepository;
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
@RequestMapping("/api/zudiscipcref")
public class ZUDiscipCrefController {

    private final Logger logger = LoggerFactory.getLogger(ZUDiscipCrefController.class);

    private final ZUDiscipCrefRepository zudiscipCrefRepository;

    public ZUDiscipCrefController(ZUDiscipCrefRepository zudiscipCrefRepository) {
        this.zudiscipCrefRepository = zudiscipCrefRepository;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterZUDiscipCref(@RequestBody ZUDiscipCref zudiscipCref, UriComponentsBuilder builder) {
        logger.info("Tentative d'ajout d'un nouveau ZUDiscipCref avec la référence : " + zudiscipCref.getRefunite());

        // Votre logique d'ajout ici
        zudiscipCrefRepository.save(zudiscipCref);

        URI location = builder.path("/api/zudiscipcref/{id}").buildAndExpand(zudiscipCref.getRefunite()).toUri();
        return ResponseEntity.created(location).body(zudiscipCref);
    }

    @GetMapping("/liste")
    public Page<ZUDiscipCref> listeZUDiscipCref(Pageable pageable) {
        logger.info("Tentative de récupération d'une liste paginée de ZUDiscipCref.");
        return zudiscipCrefRepository.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerZUDiscipCref(@PathVariable String id) {
        logger.info("Tentative de suppression d'un ZUDiscipCref avec la référence : " + id);
        zudiscipCrefRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZUDiscipCref> detailZUDiscipCref(@PathVariable String id) {
        logger.info("Tentative de récupération du détail d'un ZUDiscipCref avec la référence : " + id);

        Optional<ZUDiscipCref> optionalZUDiscipCref = zudiscipCrefRepository.findById(id);
        if (optionalZUDiscipCref.isPresent()) {
            logger.debug("Succès du détail du ZUDiscipCref avec la référence : " + id);
            return ResponseEntity.ok(optionalZUDiscipCref.get());
        } else {
            logger.debug("ZUDiscipCref introuvable avec la référence : " + id);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZUDiscipCref> modifierZUDiscipCref(@PathVariable String id, @RequestBody ZUDiscipCref updatedZUDiscipCref) {
        logger.info("Tentative de mise à jour d'un ZUDiscipCref avec la référence : " + id);

        Optional<ZUDiscipCref> optionalZUDiscipCref = zudiscipCrefRepository.findById(id);
        if (optionalZUDiscipCref.isPresent()) {
            ZUDiscipCref existingZUDiscipCref = optionalZUDiscipCref.get();

            // Mettre à jour les champs modifiables
            existingZUDiscipCref.setRefcref(updatedZUDiscipCref.getRefcref());

            zudiscipCrefRepository.save(existingZUDiscipCref);
            logger.debug("Succès de la mise à jour du ZUDiscipCref");
            return ResponseEntity.ok(existingZUDiscipCref);
        } else {
            logger.debug("Échec, ZUDiscipCref introuvable");
            return ResponseEntity.notFound().build();
        }
    }
}
