package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZUnite;
import be.eafcuccle.projint.inventairebackend.repository.ZUniteRepository;
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
@RequestMapping("/api/zunite")
public class ZUniteController {

    private final Logger logger = LoggerFactory.getLogger(ZUniteController.class);

    private int currentId = 1; // Initialisation du compteur

    @Autowired
    private ZUniteRepository zuniteRepository;


    @GetMapping("/liste")
    public ResponseEntity<Page<ZUnite>> listeZUnite(Pageable pageable, Authentication authentication) {
        if(hasAuthority(authentication, "SCOPE_read:information")){
            logger.info("Tentative de récupération d'une liste paginée de ZUnites.");
            Page<ZUnite> zunite = zuniteRepository.findAll(pageable);
            return ResponseEntity.ok(zunite);
        }else{
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission d'accéder à la liste des unités.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterZUnite(@RequestBody ZUnite zunite, UriComponentsBuilder builder, Authentication authentication) {
        if(hasAuthority(authentication, "SCOPE_write:information")){
            logger.info("Tentative d'ajout d'une nouvelle ZUnite avec l'ID : " + zunite.getIdunite());

            // Générer un nouvel ID unique
            String newId = ZUnite.generateNewId(currentId++);
            zunite.setIdunite(newId);
            zunite.setDatemaj(new Date()); // Définir la date actuelle

            zuniteRepository.save(zunite);

            URI localisation = builder.path("/api/zunite/{id}").buildAndExpand(zunite.getIdunite()).toUri();
            return ResponseEntity.created(localisation).body(zunite);

        }else{
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission d'ajouter une nouvelle unité.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerZUnite(@PathVariable String id, Authentication authentication) {
        if (hasAuthority(authentication, "SCOPE_write:information")) {
            logger.info("Tentative de suppression d'une ZUnite avec l'ID : " + id);
            zuniteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission de supprimer une unité.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ZUnite> detailZUnite(@PathVariable String id, Authentication authentication) {
        if (hasAuthority(authentication, "SCOPE_read:information")) {
            logger.info("Tentative de récupération d'un ZUnite avec l'ID : " + id);

            Optional<ZUnite> optionalZUnite = zuniteRepository.findById(id);
            if (optionalZUnite.isPresent()) {
                ZUnite existingUnite = optionalZUnite.get();
                logger.debug("Succès du détail de la ZUnite avec l'ID : " + id);
                return ResponseEntity.ok(existingUnite);
            } else {
                logger.debug("ZUnite introuvable avec l'ID : " + id);
                return ResponseEntity.notFound().build();
            }
        }else{
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission d'afficher les détails d'une unité.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }


    @PutMapping("/{id}")
    public ResponseEntity<ZUnite> modifierZUnite(@PathVariable String id, @RequestBody ZUnite updatedZUnite, Authentication authentication) {
        if (hasAuthority(authentication, "SCOPE_read:information")) {
            logger.info("Tentative de mise à jour d'une ZUnite avec l'ID : " + id);

            Optional<ZUnite> optionalZUnite = zuniteRepository.findById(id);
            if (optionalZUnite.isPresent()) {
                ZUnite existingUnite = optionalZUnite.get();

                // Mettre à jour tous les champs de l'entité ZUnite
                existingUnite.setIstrans(updatedZUnite.getIstrans());
                existingUnite.setPreflang(updatedZUnite.getPreflang());
                existingUnite.setNom(updatedZUnite.getNom());
                existingUnite.setNomUK(updatedZUnite.getNomUK());
                existingUnite.setSigle(updatedZUnite.getSigle());
                existingUnite.setDescription(updatedZUnite.getDescription());
                existingUnite.setDescriptionUK(updatedZUnite.getDescriptionUK());
                existingUnite.setRue(updatedZUnite.getRue());
                existingUnite.setNumero(updatedZUnite.getNumero());
                existingUnite.setBoite(updatedZUnite.getBoite());
                existingUnite.setLocalite(updatedZUnite.getLocalite());
                existingUnite.setCodepostal(updatedZUnite.getCodepostal());
                existingUnite.setCpi(updatedZUnite.getCpi());
                existingUnite.setLocalisation(updatedZUnite.getLocalisation());
                existingUnite.setTelephone(updatedZUnite.getTelephone());
                existingUnite.setFax(updatedZUnite.getFax());
                existingUnite.setEmail(updatedZUnite.getEmail());
                existingUnite.setSite1(updatedZUnite.getSite1());
                existingUnite.setSite2(updatedZUnite.getSite2());
                existingUnite.setLienthese(updatedZUnite.getLienthese());
                existingUnite.setLienpublica(updatedZUnite.getLienpublica());
                existingUnite.setDatedeb(updatedZUnite.getDatedeb());
                existingUnite.setDatefin(updatedZUnite.getDatefin());
                existingUnite.setDatemaj(new Date());
                existingUnite.setRemarque(updatedZUnite.getRemarque());
                existingUnite.setNbvisit(updatedZUnite.getNbvisit());
                existingUnite.setBrouillon(updatedZUnite.getBrouillon());
                existingUnite.setPrefPublication(updatedZUnite.getPrefPublication());
                existingUnite.setStatExport(updatedZUnite.getStatExport());
                existingUnite.setStatProjetcv(updatedZUnite.getStatProjetcv());
                existingUnite.setStatAnciensmembres(updatedZUnite.getStatAnciensmembres());
                existingUnite.setStatDelegue(updatedZUnite.getStatDelegue());
                existingUnite.setStatAdzion(updatedZUnite.getStatAdzion());
                existingUnite.setNiveau(updatedZUnite.getNiveau());

                zuniteRepository.save(existingUnite);
                logger.debug("Succès de la mise à jour de la ZUnite");
                return ResponseEntity.ok(existingUnite);
            } else {
                logger.debug("Échec, ZUnite introuvable");
                return ResponseEntity.notFound().build();
            }
        } else {
            logger.debug("Accès refusé ! L'utilisateur n'a pas la permission de mettre à jour une unité.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

    public static boolean hasAuthority(Authentication authentication, String expectedAuthority) {
        return authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(expectedAuthority));
    }
}
