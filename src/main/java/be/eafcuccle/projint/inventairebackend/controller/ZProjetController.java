package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZChercheur;
import be.eafcuccle.projint.inventairebackend.model.ZProjet;
import be.eafcuccle.projint.inventairebackend.repository.ZProjetRepository;
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
@RequestMapping("/api/zprojet")
public class ZProjetController {

    private final Logger logger = LoggerFactory.getLogger(ZProjetController.class);

    private final ZProjetRepository zprojetRepository;
    private int currentId = 1; // Initialisation du compteur

    public ZProjetController(ZProjetRepository zprojetRepository) {
        this.zprojetRepository = zprojetRepository;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterZProjet(@RequestBody ZProjet zprojet, UriComponentsBuilder builder) {
        logger.info("Tentative d'ajout d'un nouveau ZProjet avec l'ID : " + zprojet.getIdprojet());

        // Affecter un nouvel ID unique en utilisant le compteur
        zprojet.setIdprojet(currentId++); // Utilisation du compteur et incrémentation
        zprojet.setDatemaj(new Date()); // Définir la date actuelle

        zprojetRepository.save(zprojet);

        URI localisation = builder.path("/api/zprojet/{id}").buildAndExpand(zprojet.getIdprojet()).toUri();
        return ResponseEntity.created(localisation).body(zprojet);
    }

    @GetMapping("/liste")
    public Page<ZProjet> listeZProjets(Pageable pageable) {
        logger.info("Tentative de récupération d'une liste paginée de ZProjets.");
        return zprojetRepository.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerZProjets(@PathVariable int id) {
        logger.info("Tentative de suppression d'un ZProjet avec l'ID : " + id);
        zprojetRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ZProjet> detailZProjet(@PathVariable int id) {
        logger.info("Tentative de récupération du détail d'un ZProjet avec l'ID : " + id);

        Optional<ZProjet> optionalZProjet = zprojetRepository.findById(id);
        if (optionalZProjet.isPresent()) {
            logger.debug("Succès du détail du ZProjet avec l'ID : " + id);
            return ResponseEntity.ok(optionalZProjet.get());
        } else {
            logger.debug("ZProjet introuvable avec l'ID : " + id);
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ZProjet> modifierZProjet(@PathVariable int id, @RequestBody ZProjet updatedZProjet) {
        logger.info("Tentative de mise à jour d'un ZProjet avec l'ID : " + id);

        Optional<ZProjet> optionalZProjet = zprojetRepository.findById(id);
        if (optionalZProjet.isPresent()) {
            ZProjet existingProjet = optionalZProjet.get();

            // Mettre à jour les champs modifiables
            existingProjet.setNom(updatedZProjet.getNom());
            existingProjet.setNomUK(updatedZProjet.getNomUK());
            existingProjet.setNomprogramme(updatedZProjet.getNomprogramme());
            existingProjet.setNomprogrammeUK(updatedZProjet.getNomprogrammeUK());
            existingProjet.setResume(updatedZProjet.getResume());
            existingProjet.setResumeUK(updatedZProjet.getResumeUK());
            existingProjet.setDatedebut(updatedZProjet.getDatedebut());
            existingProjet.setDatefin(updatedZProjet.getDatefin());
            existingProjet.setDatemaj(new Date()); // Défini la date actuelle
            existingProjet.setOrdre(updatedZProjet.getOrdre());
            existingProjet.setSite(updatedZProjet.getSite());
            existingProjet.setDDebut(updatedZProjet.getDDebut());
            existingProjet.setDFin(updatedZProjet.getDFin());
            existingProjet.setFromCv(updatedZProjet.getFromCv());

            zprojetRepository.save(existingProjet);
            logger.debug("Succès de la mise à jour du ZProjet");
            return ResponseEntity.ok(existingProjet);
        } else {
            logger.debug("Échec, ZProjet introuvable");
            return ResponseEntity.notFound().build();
        }
    }

}
