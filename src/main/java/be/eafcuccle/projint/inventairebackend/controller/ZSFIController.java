package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZSFI;
import be.eafcuccle.projint.inventairebackend.repository.ZSFIRepository;
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
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/zsfi")
public class ZSFIController {

    private final Logger logger = LoggerFactory.getLogger(ZSFIController.class);

    @Autowired
    private ZSFIRepository zsfiRepository;
    private int currentId = 1;


    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterZSFI(@RequestBody ZSFI zsfi, UriComponentsBuilder builder, Authentication authentication) {
        if(hasAuthority(authentication, "SCOPE_write:information")){
            logger.info("Tentative d'ajout d'un nouveau SFI avec l'ID : " + zsfi.getIddepart());

            zsfi.setIddepart(currentId++);

            zsfiRepository.save(zsfi);

            URI localisation = builder.path("/api/zsfi/{id}").buildAndExpand(zsfi.getIddepart()).toUri();
            return ResponseEntity.created(localisation).body(zsfi);
        }else{
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission d'ajouter un nouveau SFI.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/liste")
    public Page<ZSFI> listeZSFI(Pageable pageable, Authentication authentication) {
        if(hasAuthority(authentication, "SCOPE_read:information")){
            logger.info("Tentative de récupération d'une liste paginée de SFI.");
            return zsfiRepository.findAll(pageable);
        }else{
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission d'accéder à la liste des SFIs.");
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerZSFI(@PathVariable int id, Authentication authentication) {
        if (hasAuthority(authentication, "SCOPE_write:information")) {
            logger.info("Tentative de suppression d'un SFI avec l'ID : " + id);
            zsfiRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission de supprimer un SFI.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }


    @PutMapping("/{id}")
    public ResponseEntity<ZSFI> modifierZSFI(@PathVariable int id, @RequestBody ZSFI updatedSFI, Authentication authentication) {
        if (hasAuthority(authentication, "SCOPE_read:information")) {
            logger.info("Tentative de mise à jour d'un SFI avec l'ID : " + id);

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
        }else{
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission d'afficher les détails d'un SFI.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    public static boolean hasAuthority(Authentication authentication, String expectedAuthority) {
        return authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(expectedAuthority));
    }
}
