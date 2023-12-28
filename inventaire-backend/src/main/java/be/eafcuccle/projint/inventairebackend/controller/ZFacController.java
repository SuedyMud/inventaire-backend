package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZFac; // Modification de l'import
import be.eafcuccle.projint.inventairebackend.repository.ZFacRepository; // Modification de l'import
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
@RequestMapping("/api/zfac") // Modification du mapping
public class ZFacController {

    private final Logger logger = LoggerFactory.getLogger(ZFacController.class);

    private final ZFacRepository zfacRepository; // Modification du nom de la variable
    private int currentId = 1; // Initialisation du compteur

    public ZFacController(ZFacRepository zfacRepository) { // Modification du nom du paramètre
        this.zfacRepository = zfacRepository; // Modification de l'assignation
    }


    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterZFac(@RequestBody ZFac zfac, UriComponentsBuilder builder) {
        logger.info("Tentative d'ajout d'une nouvelle ZFac avec l'ID : " + zfac.getFac());

        // Affecter un nouvel ID unique en utilisant le compteur
        zfac.setFac(String.valueOf(currentId++)); // Conversion de l'ID en chaîne de caractères et incrémentation
        zfac.setDMaj(new Date()); // Définir la date actuelle
        zfacRepository.save(zfac);

        URI localisation = builder.path("/api/zfac/{id}").buildAndExpand(zfac.getFac()).toUri();
        return ResponseEntity.created(localisation).body(zfac);
    }


    @GetMapping("/liste")
    public Page<ZFac> listeZFac(Pageable pageable) { // Modification du nom de la méthode et du type de retour
        logger.info("Tentative de récupération d'une liste paginée de ZFacs."); // Modification du nom de l'objet
        return zfacRepository.findAll(pageable); // Modification du nom de la variable
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerZFac(@PathVariable String id) {
        logger.info("Tentative de suppression d'un zfac avec FAC : " + id);
        zfacRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZFac> modifierZFac(@PathVariable String id, @RequestBody ZFac updatedZFac) {
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
    }

}
