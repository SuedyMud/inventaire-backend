package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZFac;
import be.eafcuccle.projint.inventairebackend.model.ZUFac;
import be.eafcuccle.projint.inventairebackend.repository.ZUFacRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/api/zufac")
public class ZUFacController {

    private final Logger logger = LoggerFactory.getLogger(ZUFacController.class);

    private final ZUFacRepository zufacRepository;

    public ZUFacController(ZUFacRepository zufacRepository) {
        this.zufacRepository = zufacRepository;
    }

    @GetMapping("/{idunite}")
    public List<ZFac> getFacByUnite(@PathVariable String idunite) {
        logger.info("Tentative de récupération d'une liste de ZUFac avec la référence : " + idunite);

        List<ZUFac> zufac = zufacRepository.findByZunite_Idunite(idunite);
        return zufac.stream()
                .map(ZUFac::getFac)
                .distinct()
                .collect(Collectors.toList());
    }

}
