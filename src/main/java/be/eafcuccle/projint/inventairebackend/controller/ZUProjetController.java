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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ZUProjetController {

    private final Logger logger = LoggerFactory.getLogger(ZUProjetController.class);

    @Autowired
    private ZUProjetRepository zuprojetRepository;

   /*@GetMapping("/{idunite}/responsables")
    public List<ZProjet> getResponsablesUnite(@PathVariable String idunite, Authentication authentication) {
       if(hasAuthority(authentication, "SCOPE_read:information")){
        List<ZUProjet> zuprojet = zuprojetRepository.findByZunite_Idunite(idunite);
        return zuprojet.stream()
                .map(ZUProjet::getProjet)
                .collect(Collectors.toList());
       }else{
           logger.debug("Accès refusé ! L'utilisateur n'a pas la permission d'accéder à la liste des projets.");
           return null;
       }
    }*/

    /*@GetMapping("/{idprojet}/zunite")
    public List<ZUnite> getUnitesByProjet(@PathVariable Integer idprojet) {
        return zuprojetRepository.findUnitesByProjetId(idprojet);
    }

    @GetMapping("/zuprojet/liste")
    public Page<ZProjet> listeZUCompos(Pageable pageable){
        logger.info("Tentative de récupération d'une liste paginée de relation ZUProjet");
        return zuprojetRepository.findAll(pageable);
    }*/

   /* public static boolean hasAuthority(Authentication authentication, String expectedAuthority) {
        return authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(expectedAuthority));
    }*/

}
