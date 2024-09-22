package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZDiscipCref;
import be.eafcuccle.projint.inventairebackend.model.ZUnite;
import be.eafcuccle.projint.inventairebackend.repository.ZDiscipCrefRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/zdiscipcref")
public class ZDiscipCrefController {

    private final Logger logger = LoggerFactory.getLogger(ZDiscipCrefController.class);

    @Autowired
    private ZDiscipCrefRepository zdiscipCrefRepository;
    /*private int currentId = 1;*/


    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterZDiscipCref(@RequestBody ZDiscipCref zdiscipCref, UriComponentsBuilder builder, Authentication authentication) {
        if(hasAuthority(authentication, "SCOPE_write:information")){
            logger.info("Tentative d'ajout d'une nouvelle discipline avec l'ID : " + zdiscipCref.getIdcodecref());


           // zdiscipCref.setIdcodecref(String.valueOf(currentId++)); // Conversion de l'ID en chaîne de caractères et incrémentation

            zdiscipCrefRepository.save(zdiscipCref);

            URI localisation = builder.path("/api/zdiscipcref/{id}").buildAndExpand(zdiscipCref.getIdcodecref()).toUri();
            return ResponseEntity.created(localisation).body(zdiscipCref);
        }else{
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission d'ajouter une nouvelle discipline.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

    @GetMapping("/liste")
    public Page<ZDiscipCref> listeZDiscipCref(Pageable pageable, Authentication authentication) {
        if(hasAuthority(authentication, "SCOPE_read:information")) {
            logger.info("Tentative de récupération d'une liste paginée de disciplines.");
            return zdiscipCrefRepository.findAll(pageable);

        }else{
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission d'accéder à la liste des disciplines.");
            return null;
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerZDiscipCref(@PathVariable String id, Authentication authentication) {
        if (hasAuthority(authentication, "SCOPE_write:information")) {
            logger.info("Tentative de suppression d'une discipline avec l'ID : " + id);
            zdiscipCrefRepository.deleteById(id);
            return ResponseEntity.noContent().build();

        }else{
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission de supprimer une discipline.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ZDiscipCref> detailZDiscipCref(@PathVariable String id, Authentication authentication) {
        if (hasAuthority(authentication, "SCOPE_read:information")) {
            logger.info("Tentative de récupération d'un discipline avec l'ID : " + id);

            Optional<ZDiscipCref> optionalZDiscipCref = zdiscipCrefRepository.findById(id);
            if (optionalZDiscipCref.isPresent()) {
                ZDiscipCref existingDiscipCref = optionalZDiscipCref.get();
                logger.debug("Succès du détail de la ZDiscipCref avec l'ID : " + id);
                return ResponseEntity.ok(existingDiscipCref);
            } else {
                logger.debug("ZDiscipCref introuvable avec l'ID : " + id);
                return ResponseEntity.notFound().build();
            }
        }else{
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission d'afficher les détails d'une discipline.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }


    @PutMapping("/{id}")
    public ResponseEntity<ZDiscipCref> modifierZDiscipCref(@PathVariable String id, @RequestBody ZDiscipCref updatedDiscipCref, Authentication authentication) {
        if (hasAuthority(authentication, "SCOPE_read:information")) {
            logger.info("Tentative de mise à jour d'une discipline avec l'ID : " + id);

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
        } else {
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission de mettre à jour une discipline.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    public static boolean hasAuthority(Authentication authentication, String expectedAuthority) {
        return authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(expectedAuthority));
    }
}
