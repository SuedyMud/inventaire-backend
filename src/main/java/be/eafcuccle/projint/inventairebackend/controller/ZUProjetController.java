package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.*;
import be.eafcuccle.projint.inventairebackend.repository.ZUProjetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/zuprojet")
public class ZUProjetController {

    private final Logger logger = LoggerFactory.getLogger(ZUProjetController.class);

    @Autowired
    private ZUProjetRepository zuprojetRepository;


    @GetMapping("/{idprojet}/unites")
    public List<ZUnite> getUnitesByProjet(@PathVariable Integer idprojet) {
        return zuprojetRepository.findUnitesByProjetId(idprojet);
    }

    @GetMapping("/{idunite}/projets")
    public List<ZProjet> getProjetByUnite(@PathVariable String idunite) {
        logger.info("Tentative de récupération d'une liste de ZUProjet avec la référence : " + idunite);

        // Appel du repository pour récupérer les projets associés à l'unité
        List<ZUProjet> zuprojet = zuprojetRepository.findByZunite_Idunite(idunite);

        return zuprojet.stream()
                .map(ZUProjet::getZprojet)
                .distinct()
                .collect(Collectors.toList());
    }



 /*   @GetMapping("/{idunite}/responsables")
    public List<ZProjet> getResponsablesUnite(@PathVariable String idunite, Authentication authentication) {
        if (hasAuthority(authentication, "SCOPE_read:information")) {
            List<ZUProjet> zuprojet = zuprojetRepository.findByZunite_Idunite(idunite); // Correction ici
            return zuprojet.stream()
                    .map(ZUProjet::getProjet)
                    .collect(Collectors.toList());
        } else {
            logger.debug("Accès refusé !");
            return Collections.emptyList(); // retourne une liste vide en cas d'accès refusé
        }
    }*/

    public static boolean hasAuthority(Authentication authentication, String expectedAuthority) {
        return authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(expectedAuthority));
    }
}
