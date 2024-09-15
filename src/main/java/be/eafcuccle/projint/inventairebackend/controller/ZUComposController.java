package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZChercheur;
import be.eafcuccle.projint.inventairebackend.model.ZUCompos;
import be.eafcuccle.projint.inventairebackend.repository.ZUComposRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/zcompos")
public class ZUComposController {

    private final Logger logger = LoggerFactory.getLogger(ZUComposController.class);

    private final ZUComposRepository zucomposRepository;

    public ZUComposController(ZUComposRepository zucomposRepository) {
        this.zucomposRepository = zucomposRepository;
    }

    // Récupérer les responsables d'une unité
    @GetMapping("/{idunite}/responsables")
    public ResponseEntity<List<ZChercheur>> getResponsablesUnite(@PathVariable String idunite) {
        logger.info("Récupération des responsables pour l'unité avec idunite : " + idunite);

        List<ZUCompos> responsablesCompos = zucomposRepository.findByZunite_IduniteAndResponsable(idunite, "oui");
        if (responsablesCompos.isEmpty()) {
            logger.warn("Aucun responsable trouvé pour l'unité avec idunite : " + idunite);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<ZChercheur> responsables = responsablesCompos.stream()
                .map(ZUCompos::getChercheur)
                .distinct()
                .collect(Collectors.toList());

        return ResponseEntity.ok(responsables);
    }

    // Récupérer les responsables d'une unité
    @GetMapping("/{idunite}/membres")
    public ResponseEntity<List<ZChercheur>> getMembresUnite(@PathVariable String idunite) {
        logger.info("Récupération des membres pour l'unité avec idunite : " + idunite);

        // Utiliser la méthode findByZunite_IduniteAndResponsable pour récupérer les membres
        List<ZUCompos> membresCompos = zucomposRepository.findByZunite_IduniteAndResponsable(idunite, "non");
        if (membresCompos.isEmpty()) {
            logger.warn("Aucun membre trouvé pour l'unité avec idunite : " + idunite);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Supprimez .distinct() si vous voulez afficher tous les membres sans filtrer les doublons
        List<ZChercheur> membres = membresCompos.stream()
                .map(ZUCompos::getChercheur)  // Récupérer tous les chercheurs associés
                .distinct()  // Supprimer les doublons
                .collect(Collectors.toList());

        return ResponseEntity.ok(membres);
    }





   /* @GetMapping("/{idunite}/projet/{idprojet}/responsables")
    public ResponseEntity<List<ZChercheur>> getResponsablesByProjet(@PathVariable String idunite, @PathVariable Integer idprojet) {
        logger.info("Récupération des responsables pour l'unité avec idunite : " + idunite + " et le projet avec idprojet : " + idprojet);

        List<ZChercheur> responsables = zucomposRepository.findResponsablesByProjetIdAndUniteId(idunite, idprojet);
        if (responsables.isEmpty()) {
            logger.warn("Aucun responsable trouvé pour l'unité avec idunite : " + idunite + " et le projet avec idprojet : " + idprojet);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(responsables);
    }

    // Retrieve the researchers for a project and a unit
    @GetMapping("/{idunite}/projet/{idprojet}/chercheurs")
    public ResponseEntity<List<ZChercheur>> getChercheursByProjet(@PathVariable String idunite, @PathVariable Integer idprojet) {
        logger.info("Récupération des chercheurs pour l'unité avec idunite : " + idunite + " et le projet avec idprojet : " + idprojet);

        List<ZChercheur> chercheurs = zucomposRepository.findChercheursByProjetIdAndUniteId(idunite, idprojet);
        if (chercheurs.isEmpty()) {
            logger.warn("Aucun chercheur trouvé pour l'unité avec idunite : " + idunite + " et le projet avec idprojet : " + idprojet);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(chercheurs);
    }*/

}
