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

    private final ZUComposRepository zucomposRepository;

    public ZUComposController(ZUComposRepository zUComposRepository) {
        this.zucomposRepository = zUComposRepository;
    }

    @GetMapping("/{idunite}/responsables")
    public List<ZChercheur> getResponsablesUnite(@PathVariable String idunite) {
        List<ZUCompos> responsablesCompos = zucomposRepository.findByZunite_IduniteAndResponsable(idunite, "oui");
        return responsablesCompos.stream()
                .map(ZUCompos::getChercheur)
                .collect(Collectors.toList());
    }

    @PostMapping("/zcompos/ajouter")
    public ResponseEntity<?> ajouterZUCompos(@RequestBody ZUCompos zUCompos) {
        logger.info("Tentative d'ajout d'une nouvelle relation ZUCompos.");
        zucomposRepository.save(zUCompos);
        return ResponseEntity.status(HttpStatus.CREATED).body(zUCompos);
    }

    @GetMapping("/zcompos/liste")
    public Page<ZUCompos> listeZUCompos(Pageable pageable) {
        logger.info("Tentative de récupération d'une liste paginée de relations ZUCompos.");
        return zucomposRepository.findAll(pageable);
    }

}
