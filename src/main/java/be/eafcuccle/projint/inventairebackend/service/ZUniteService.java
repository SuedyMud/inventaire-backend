package be.eafcuccle.projint.inventairebackend.service;

import be.eafcuccle.projint.inventairebackend.model.ZChercheur;
import be.eafcuccle.projint.inventairebackend.repository.ZUniteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZUniteService {
    @Autowired
    private ZUniteRepository zuniteRepository;

    public List<ZChercheur> getChercheursByUniteId(String idUnite) {
        return zuniteRepository.findChercheursByUniteId(idUnite);
    }
}
