package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZChercheur;
import be.eafcuccle.projint.inventairebackend.model.ZUCompos;
import be.eafcuccle.projint.inventairebackend.repository.ZUComposRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ZUComposController {

    private final Logger logger = LoggerFactory.getLogger(ZUComposController.class);

    private final ZUComposRepository zucomposrepository;

    public ZUComposController(ZUComposRepository zUComposRepository) {
        this.zucomposrepository = zUComposRepository;
    }
  /*  @GetMapping("/zunite/{id}/responsable")
    public ResponseEntity<ZChercheur> getResponsableUnite(@PathVariable String id) {
        ZUCompos responsableCompos = zucomposrepository.findByIdAndResponsable(id, "Oui");
        return responsableCompos != null ? ResponseEntity.ok(responsableCompos.getChercheur()) : ResponseEntity.notFound().build();
    }*/


    /*@GetMapping("/unite/{idunite}/chercheurs")
    public ResponseEntity<List<ZChercheur>> getChercheursByUnite(@PathVariable String idunite) {
        List<ZUCompos> uComposList = zucomposrepository.findByIdUnite(idunite);
        List<ZChercheur> chercheurs = uComposList.stream().map(ZUCompos::getZchercheur).collect(Collectors.toList());
        return ResponseEntity.ok(chercheurs);
    }*/

    @PostMapping("/zcompos/ajouter")
    public ResponseEntity<?> ajouterZUCompos(@RequestBody ZUCompos zUCompos) {
        logger.info("Tentative d'ajout d'une nouvelle relation ZUCompos.");
        zucomposrepository.save(zUCompos);
        return ResponseEntity.status(HttpStatus.CREATED).body(zUCompos);
    }

    @GetMapping("/zcompos/liste")
    public Page<ZUCompos> listeZUCompos(Pageable pageable) {
        logger.info("Tentative de récupération d'une liste paginée de relations ZUCompos.");
        return zucomposrepository.findAll(pageable);
    }
/*
    @DeleteMapping("/{zchercheurId}/{zuniteId}")
    public ResponseEntity<?> supprimerZUCompos(@PathVariable int zchercheurId, @PathVariable String zuniteId) {
        logger.info("Tentative de suppression de la relation ZUCompos avec les IDs ZChercheur : " + zchercheurId + " et ZUnite : " + zuniteId);
        zUComposRepository.deleteById(zchercheurId, zuniteId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/zchercheur/{id}")
    public ResponseEntity<ZChercheur> detailZChercheur(@PathVariable int id) {
        logger.info("Tentative de récupération du détail d'un zchercheur avec l'ID : " + id);
        Optional<ZChercheur> optionalZChercheur = zUComposRepository.findZChercheurById(id);
        return optionalZChercheur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/zunite/{id}")
    public ResponseEntity<ZUnite> detailZUnite(@PathVariable String id) {
        logger.info("Tentative de récupération du détail d'une zunité avec l'ID : " + id);
        Optional<ZUnite> optionalZUnite = zUComposRepository.findZUniteById(id);
        return optionalZUnite.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
*/
}
