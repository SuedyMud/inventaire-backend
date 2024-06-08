package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZChercheur;
import be.eafcuccle.projint.inventairebackend.repository.ZChercheurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/*import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;*/
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/zchercheur")
public class ZChercheurController {

    private final Logger logger = LoggerFactory.getLogger(ZChercheurController.class);

    private final ZChercheurRepository zchercheurRepository;

    private int currentId = 1; // Initialisation du compteur

    public ZChercheurController(ZChercheurRepository zchercheurRepository) {
        this.zchercheurRepository = zchercheurRepository;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterZChercheur(@RequestBody ZChercheur zchercheur, UriComponentsBuilder builder) {
        logger.info("Tentative d'ajout d'un nouveau zchercheur avec l'ID : " + zchercheur.getIdche());

        // Affecter un nouvel ID unique en utilisant le compteur
        zchercheur.setIdche(currentId++); // Utilisation du compteur et incrémentation
        zchercheur.setDDig(new Date()); // Définir la date actuelle

        zchercheurRepository.save(zchercheur);

        URI localisation = builder.path("/api/zchercheurs/{id}").buildAndExpand(zchercheur.getIdche()).toUri();
        return ResponseEntity.created(localisation).body(zchercheur);
    }


    @GetMapping("/liste")
    public Page<ZChercheur> listeZChercheur(Pageable pageable) {

        logger.info("Tentative de récupération d'une liste paginée de zchercheurs.");
        return zchercheurRepository.findAll(pageable);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerZChercheur(@PathVariable int id) {
        logger.info("Tentative de suppression d'un zchercheur avec l'ID : " + id);
        zchercheurRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //pour après lorque je vais ajouter les permissions
    /*
    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerChercheur(@PathVariable int id, Authentication authentication) {
        if (hasAuthority(authentication, "SCOPE_write:information")) {
            logger.info("Tentative de suppression d'un chercheur avec l'ID : " + id);
            zchercheurRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission de supprimer un chercheur.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }*/

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ZChercheur> detailZChercheur(@PathVariable int id) {
        logger.info("Tentative de  récupération du détail d'un zchercheur avec l'ID : " + id);

        Optional<ZChercheur> optionalZChercheur = zchercheurRepository.findById(id);
        if (optionalZChercheur.isPresent()) {
            logger.debug("Succès du détail du zchercheur avec l'ID : " + id);


            return ResponseEntity.ok(optionalZChercheur.get());
        } else {
            logger.debug("ZChercheur introuvable avec l'ID : " + id);
            return ResponseEntity.notFound().build();
        }
    }


    /*@GetMapping("/liste")
    public Page<ZChercheur> listeZChercheurs(Pageable pageable) {
        logger.info("Tentative de récupération d'une liste paginée de zchercheurs.");
        return zchercheurRepository.findAll(pageable);
    }
*/

    @PutMapping("/{id}")
    public ResponseEntity<ZChercheur> modifierZChercheur(@PathVariable int id, @RequestBody ZChercheur updatedZChercheur) {
        logger.info("Tentative de mise à jour d'un zchercheur avec l'ID : " + id);

        Optional<ZChercheur> optionalZChercheur = zchercheurRepository.findById(id);
        if (optionalZChercheur.isPresent()) {
            ZChercheur existingChercheur = optionalZChercheur.get();

            // Mettre à jour les champs modifiables
            existingChercheur.setNom(updatedZChercheur.getNom());
            existingChercheur.setPrenom(updatedZChercheur.getPrenom());
            existingChercheur.setTitre(updatedZChercheur.getTitre());
            existingChercheur.setMatricule(updatedZChercheur.getMatricule());
            existingChercheur.setCpi(updatedZChercheur.getCpi());
            existingChercheur.setTelephone(updatedZChercheur.getTelephone());
            existingChercheur.setEmail(updatedZChercheur.getEmail());
            existingChercheur.setFax(updatedZChercheur.getFax());
            existingChercheur.setSite(updatedZChercheur.getSite());
            existingChercheur.setCorps(updatedZChercheur.getCorps());
            existingChercheur.setCorpsOrdre(updatedZChercheur.getCorpsOrdre());

            // Ne pas oublier de valeur dMaj avant de sauvegarder !! sinon erreur
            existingChercheur.setDDig(new Date()); // Défini la date actuelle

            existingChercheur.setFacChe(updatedZChercheur.getFacChe());
            existingChercheur.setPrefPublication(updatedZChercheur.getPrefPublication());



            zchercheurRepository.save(existingChercheur);
            logger.debug("Succès de la mise à jour du zchercheur");
            return ResponseEntity.ok(existingChercheur);
        } else {
            logger.debug("Échec, zchercheur introuvable");
            return ResponseEntity.notFound().build();
        }



    }



    /*
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
