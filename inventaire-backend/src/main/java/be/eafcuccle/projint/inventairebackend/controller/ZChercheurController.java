package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZChercheur;
import be.eafcuccle.projint.inventairebackend.repository.ZChercheurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/*import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;*/
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/zchercheur")
public class ZChercheurController {

    private final Logger logger = LoggerFactory.getLogger(ZChercheurController.class);

    private final ZChercheurRepository zchercheurRepository;
    private int currentId = 1; // Initialisation du compteur

    public ZChercheurController(ZChercheurRepository zchercheurRepository) {
        this.zchercheurRepository = zchercheurRepository;
    }
/*
    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterChercheur(@RequestBody ZChercheur zchercheur, Authentication authentication, UriComponentsBuilder builder) {
        if (hasAuthority(authentication, "SCOPE_write:information")) {
            logger.info("Tentative d'ajout d'un nouveau chercheur avec l'ID : " + zchercheur.getIdche());

            // Générer un nouvel ID pour le chercheur
            zchercheur.setIdche(UUID.randomUUID());
            zchercheur.setDDig(new Date()); // Définir la date actuelle

            zchercheurRepository.save(chercheur);

            URI localisation = builder.path("/api/chercheurs/{id}").buildAndExpand(chercheur.getIdche()).toUri();
            return ResponseEntity.created(localisation).body(chercheur);
        } else {
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission d'ajouter un nouveau chercheur.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }*/

    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterChercheur(@RequestBody ZChercheur zchercheur, UriComponentsBuilder builder) {
        logger.info("Tentative d'ajout d'un nouveau chercheur avec l'ID : " + zchercheur.getIdche());

        // Affecter un nouvel ID unique en utilisant le compteur
        zchercheur.setIdche(currentId++); // Utilisation du compteur et incrémentation
        zchercheur.setDDig(new Date()); // Définir la date actuelle

        zchercheurRepository.save(zchercheur);

        URI localisation = builder.path("/api/zchercheurs/{id}").buildAndExpand(zchercheur.getIdche()).toUri();
        return ResponseEntity.created(localisation).body(zchercheur);
    }



    @GetMapping("/liste")
    public Page<ZChercheur> listeChercheurs(Pageable pageable) {
        logger.info("Tentative de récupération d'une liste paginée de chercheurs.");
        return zchercheurRepository.findAll(pageable);
    }

    /*
    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerChercheur(@PathVariable int id, Authentication authentication) {
        if (hasAuthority(authentication, "SCOPE_write:information")) {
            logger.info("Tentative de suppression d'un chercheur avec l'ID : " + id);
            chercheurRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission de supprimer un chercheur.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZChercheur> modifierChercheur(@PathVariable int id, @RequestBody ZChercheur updatedChercheur, Authentication authentication) {
        if (hasAuthority(authentication, "SCOPE_write:information")) {
            logger.info("Tentative de mise à jour d'un chercheur avec l'ID : " + id);

            Optional<ZChercheur> optionalChercheur = chercheurRepository.findById(id);
            if (optionalChercheur.isPresent()) {
                ZChercheur existingChercheur = optionalChercheur.get();

                // Mettre à jour les champs modifiables
                existingChercheur.setNom(updatedChercheur.getNom());
                existingChercheur.setPrenom(updatedChercheur.getPrenom());
                existingChercheur.setTitre(updatedChercheur.getTitre());
                // ... Continuer pour les autres champs

                chercheurRepository.save(existingChercheur);
                logger.debug("Succès de la mise à jour du chercheur");
                return ResponseEntity.ok(existingChercheur);
            } else {
                logger.debug("Échec, chercheur introuvable");
                return ResponseEntity.notFound().build();
            }
        } else {
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission de mettre à jour un chercheur.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
*/
    /*public static boolean hasAuthority(Authentication authentication, String expectedAuthority) {
        // Ton code existant pour vérifier les autorisations
    }*/
}
