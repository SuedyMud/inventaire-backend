package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZFac; // Modification de l'import
import be.eafcuccle.projint.inventairebackend.repository.ZFacRepository; // Modification de l'import
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


@RestController
@RequestMapping("/api/zfac")
public class ZFacController {

    private final Logger logger = LoggerFactory.getLogger(ZFacController.class);

    @Autowired
    private ZFacRepository zfacRepository;
    private int currentId = 1;


    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterZFac(@RequestBody ZFac zfac, UriComponentsBuilder builder, Authentication authentication) {
        if(hasAuthority(authentication, "SCOPE_write:all-information")){
            logger.info("Tentative d'ajout d'une nouvelle ZFac avec l'ID : " + zfac.getFac());

            // Affecter un nouvel ID unique en utilisant le compteur
            zfac.setFac(String.valueOf(currentId++)); // Conversion de l'ID en chaîne de caractères et incrémentation
            zfac.setDMaj(new Date()); // Définir la date actuelle
            zfacRepository.save(zfac);

            URI localisation = builder.path("/api/zfac/{id}").buildAndExpand(zfac.getFac()).toUri();
            return ResponseEntity.created(localisation).body(zfac);
        } else{
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission d'ajouter une nouvelle faculté");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }


    @GetMapping("/liste")
    public Page<ZFac> listeZFac(Pageable pageable, Authentication authentication) {
        if(hasAuthority(authentication, "SCOPE_read:information")){
            logger.info("Tentative de récupération d'une liste paginée de ZFacs.");
            return zfacRepository.findAll(pageable);
        } else{
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission d'accéder à la liste des facultés.");
            return null;
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerZFac(@PathVariable String id, Authentication authentication) {
        if (hasAuthority(authentication, "SCOPE_write:information")) {
            logger.info("Tentative de suppression d'un ZFac avec FAC : " + id);
            zfacRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else {
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission de supprimer une faculté.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ZFac> modifierZFac(@PathVariable String id, @RequestBody ZFac updatedZFac, Authentication authentication) {
        if (hasAuthority(authentication, "SCOPE_read:information")) {
            logger.info("Tentative de mise à jour d'une ZFac avec l'ID : " + id);

            Optional<ZFac> optionalZFac = zfacRepository.findById(id);
            if (optionalZFac.isPresent()) {
                ZFac existingFac = optionalZFac.get();

                // Mettre à jour les champs
                existingFac.setFaculte(updatedZFac.getFaculte());
                existingFac.setFaculteUK(updatedZFac.getFaculteUK());
                existingFac.setSigle(updatedZFac.getSigle());
                existingFac.setDMaj(updatedZFac.getDMaj());
                existingFac.setCc(updatedZFac.getCc());
                existingFac.setInfofin(updatedZFac.getInfofin());
                existingFac.setIdFac(updatedZFac.getIdFac());
                existingFac.setActif(updatedZFac.getActif());
                existingFac.setGroupe(updatedZFac.getGroupe());
                existingFac.setInvent20(updatedZFac.getInvent20());
                // Ne pas oublier de valeur dMaj avant de sauvegarder !! sinon erreur
                existingFac.setDMaj(new Date()); // Défini la date actuelle

                zfacRepository.save(existingFac);
                logger.debug("Succès de la mise à jour de la ZFac");
                return ResponseEntity.ok(existingFac);
            } else {
                logger.debug("Échec, ZFac introuvable");
                return ResponseEntity.notFound().build();
            }
        }else{
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission d'afficher les détails d'une faculté.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

    public static boolean hasAuthority(Authentication authentication, String expectedAuthority) {
        return authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(expectedAuthority));
    }

}
