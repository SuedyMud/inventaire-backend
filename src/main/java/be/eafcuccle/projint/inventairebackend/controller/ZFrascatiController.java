package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZFrascati;
import be.eafcuccle.projint.inventairebackend.repository.ZFrascatiRepository;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/zfrascati")
public class ZFrascatiController {

    private final Logger logger = LoggerFactory.getLogger(ZFrascatiController.class);

    @Autowired
    private ZFrascatiRepository zfrascatiRepository;

    private int currentId = 1;

    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterFrascati(@RequestBody ZFrascati zfrascati, UriComponentsBuilder builder, Authentication authentication) {
        if (hasAuthority(authentication, "SCOPE_write:information")) {
            logger.info("Tentative d'ajout d'un nouveau ZFrascati avec l'ID : " + zfrascati.getIdfrascati());

            zfrascati.setIdfrascati(String.valueOf(currentId++)); // Conversion de l'ID en chaîne de caractères et incrémentation

            zfrascatiRepository.save(zfrascati);

            URI localisation = builder.path("/api/zfrascati/{id}").buildAndExpand(zfrascati.getIdfrascati()).toUri();
            return ResponseEntity.created(localisation).body(zfrascati);
        } else {
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission d'ajouter une nouvelle frascati.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/liste")
    public Page<ZFrascati> listeFrascati(Pageable pageable, Authentication authentication) {
        if (hasAuthority(authentication, "SCOPE_read:information")) {
            logger.info("Tentative de récupération d'une liste paginée de frascati.");
            return zfrascatiRepository.findAll(pageable);
        } else {
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission d'accéder à la liste des frascatis.");
            return Page.empty();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerFrascati(@PathVariable String id, Authentication authentication) {
        if (hasAuthority(authentication, "SCOPE_write:information")) {
            logger.info("Tentative de suppression d'un frascatis avec l'ID : " + id);
            zfrascatiRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission de supprimer une frascati.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<ZFrascati> detailZFrascati(@PathVariable String id, Authentication authentication) {
        if (hasAuthority(authentication, "SCOPE_read:information")) {
            logger.info("Tentative de récupération du détail d'un frascatis avec l'ID : " + id);

            // Récupération du ZFrascati correspondant à l'ID
            Optional<ZFrascati> optionalZFrascati = zfrascatiRepository.findById(id);

            // Vérification de l'existence du ZFrascati
            if (optionalZFrascati.isPresent()) {
                ZFrascati zfrascati = optionalZFrascati.get();
                // Initialisation des relations
                Hibernate.initialize(zfrascati.getZufrascati());
                zfrascati.getZufrascati().forEach(zuf -> {
                    Hibernate.initialize(zuf.getZunite());
                    /*Hibernate.initialize(zuf.getZfrascati());*/
                });
                logger.debug("Succès du détail du ZFrascati avec l'ID : " + id);
                return ResponseEntity.ok(zfrascati);
            } else {
                logger.debug("ZFrascati introuvable avec l'ID : " + id);
                return ResponseEntity.notFound().build();
            }
        } else {
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission d'afficher les détails d'une frascati.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZFrascati> modifierFrascati(@PathVariable String id, @RequestBody ZFrascati updatedFrascati, Authentication authentication) {
        if (hasAuthority(authentication, "SCOPE_write:information")) {
            logger.info("Tentative de mise à jour d'un frascati avec l'ID : " + id);

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
        } else {
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission de mettre à jour une frascati.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    public static boolean hasAuthority(Authentication authentication, String expectedAuthority) {
        return authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(expectedAuthority));
    }
}
