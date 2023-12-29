package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZSFI;
import be.eafcuccle.projint.inventairebackend.repository.ZSFIRepository;
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
@RequestMapping("/api/zsfi")
public class ZSFIController {

    private final Logger logger = LoggerFactory.getLogger(ZSFIController.class);

    private final ZSFIRepository zsfiRepository;
    private int currentId = 1; // Initialisation du compteur

    public ZSFIController(ZSFIRepository zsfiRepository) {
        this.zsfiRepository = zsfiRepository;
    }
    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterZSFI(@RequestBody ZSFI zsfi, UriComponentsBuilder builder) {
        logger.info("Tentative d'ajout d'un nouveau ZSFI avec l'ID : " + zsfi.getIddepart());

        // Affecter un nouvel ID unique en utilisant UUID
        zsfi.setIddepart(currentId++); // Utilisation du compteur et incrémentation

        zsfiRepository.save(zsfi);

        URI localisation = builder.path("/api/zsfi/{id}").buildAndExpand(zsfi.getIddepart()).toUri();
        return ResponseEntity.created(localisation).body(zsfi);
    }

    @GetMapping("/liste")
    public Page<ZSFI> listeZSFI(Pageable pageable) {
        logger.info("Tentative de récupération d'une liste paginée de SFI.");
        return zsfiRepository.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerZSFI(@PathVariable int id) {
        logger.info("Tentative de suppression d'un ZSFI avec l'ID : " + id);
        zsfiRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZSFI> modifierZSFI(@PathVariable int id, @RequestBody ZSFI updatedSFI) {
        logger.info("Tentative de mise à jour d'un ZSFI avec l'ID : " + id);

        Optional<ZSFI> optionalSFI = zsfiRepository.findById(id);
        if (optionalSFI.isPresent()) {
            ZSFI existingSFI = optionalSFI.get();

            // Mettre à jour les champs modifiables
            existingSFI.setDepart(updatedSFI.getDepart());
            existingSFI.setDepartUK(updatedSFI.getDepartUK());
            existingSFI.setReffac(updatedSFI.getReffac());
            existingSFI.setOrdre(updatedSFI.getOrdre());

            zsfiRepository.save(existingSFI);
            logger.debug("Succès de la mise à jour du SFI");
            return ResponseEntity.ok(existingSFI);
        } else {
            logger.debug("Échec, SFI introuvable");
            return ResponseEntity.notFound().build();
        }
    }
}
