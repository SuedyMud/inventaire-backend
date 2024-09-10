package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZDiscipCref;
import be.eafcuccle.projint.inventairebackend.model.ZUDiscipCref;
import be.eafcuccle.projint.inventairebackend.repository.ZUDiscipCrefRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/zudiscipcref")
public class ZUDiscipCrefController {

    private final Logger logger = LoggerFactory.getLogger(ZUDiscipCrefController.class);

    private final ZUDiscipCrefRepository zudiscipCrefRepository;

    public ZUDiscipCrefController(ZUDiscipCrefRepository zudiscipCrefRepository) {
        this.zudiscipCrefRepository = zudiscipCrefRepository;
    }

    @GetMapping("/{idunite}")
    public List<ZDiscipCref> getFrascatiByUnite(@PathVariable String idunite) {
        logger.info("Tentative de récupération d'une liste de ZUDiscipCref avec la référence : " + idunite);

        List<ZUDiscipCref> zudiscipCref = zudiscipCrefRepository.findByZunite_Idunite(idunite);
        return zudiscipCref.stream()
                .map(ZUDiscipCref::getdiscipCref)
                .distinct()
                .collect(Collectors.toList());
    }

}
