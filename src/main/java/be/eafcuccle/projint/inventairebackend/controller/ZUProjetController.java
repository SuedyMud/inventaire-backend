package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZProjet;
import be.eafcuccle.projint.inventairebackend.model.ZUnite;
import be.eafcuccle.projint.inventairebackend.repository.ZUProjetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ZUProjetController {

    private final Logger logger = LoggerFactory.getLogger(ZUProjetController.class);

    @Autowired
    private ZUProjetRepository zuprojetRepository;


    @GetMapping("/{idprojet}/zunite")
    public List<ZUnite> getUnitesByProjet(@PathVariable Integer idprojet) {
        return zuprojetRepository.findUnitesByProjetId(idprojet);
    }

    @GetMapping("/zuprojet/liste")
    public Page<ZProjet> listeZUCompos(Pageable pageable){
        logger.info("Tentative de récupération d'une liste paginée de relation ZUProjet");
        return zuprojetRepository.findAll(pageable);
    }

}
