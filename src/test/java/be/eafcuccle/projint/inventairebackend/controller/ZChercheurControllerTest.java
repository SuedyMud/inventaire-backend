package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZChercheur;
import be.eafcuccle.projint.inventairebackend.repository.ZChercheurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ZChercheurControllerTest {

    @Mock
    private ZChercheurRepository zchercheurRepository;

    @InjectMocks
    private ZChercheurController zchercheurController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test pour la méthode ajouterZChercheur
    @Test
    void testAjouterZChercheur() {
        // Crée une instance de ZChercheur pour le test
        ZChercheur zchercheur = new ZChercheur();
        zchercheur.setIdche(1);
        zchercheur.setNom("Test Chercheur");

        // Prépare l'UriComponentsBuilder pour le test
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("");

        // Configure le comportement attendu du dépôt
        when(zchercheurRepository.save(any(ZChercheur.class))).thenReturn(zchercheur);

        // Appelle la méthode ajouterZChercheur
        ResponseEntity<?> response = zchercheurController.ajouterZChercheur(zchercheur, builder);

        // Vérifie que le statut de la réponse est 201 (Created)
        assertEquals(201, response.getStatusCodeValue());

        // Vérifie que la méthode save du dépôt a été appelée une fois
        verify(zchercheurRepository, times(1)).save(zchercheur);
    }

    // Test pour la méthode listeZChercheurs
    @Test
    void testListeZChercheurs() {
        // Prépare un objet Pageable et une page simulée contenant une liste de chercheurs
        Pageable pageable = PageRequest.of(0, 10);
        Page<ZChercheur> page = new PageImpl<>(Collections.singletonList(new ZChercheur()));

        // Configure le comportement attendu du dépôt
        when(zchercheurRepository.findAll(pageable)).thenReturn(page);

        // Appelle la méthode listeZChercheurs
        Page<ZChercheur> result = zchercheurController.listeZChercheurs(pageable);

        // Vérifie que la taille de la page retournée est correcte
        assertEquals(1, result.getTotalElements());

        // Vérifie que la méthode findAll du dépôt a été appelée une fois
        verify(zchercheurRepository, times(1)).findAll(pageable);
    }

    // Test pour la méthode supprimerZChercheur
    @Test
    void testSupprimerZChercheur() {
        // Configure le comportement attendu du dépôt
        when(zchercheurRepository.existsById(1)).thenReturn(true);

        // Appelle la méthode supprimerZChercheur
        ResponseEntity<?> response = zchercheurController.supprimerZChercheur(1);

        // Vérifie que le statut de la réponse est 204 (No Content)
        assertEquals(204, response.getStatusCodeValue());

        // Vérifie que la méthode deleteById du dépôt a été appelée une fois
        verify(zchercheurRepository, times(1)).deleteById(1);
    }

    // Test pour la méthode detailZChercheur
    @Test
    void testDetailZChercheur() {
        // Crée une instance de ZChercheur pour le test
        ZChercheur zchercheur = new ZChercheur();
        zchercheur.setIdche(1);

        // Configure le comportement attendu du dépôt
        when(zchercheurRepository.findById(1)).thenReturn(Optional.of(zchercheur));

        // Appelle la méthode detailZChercheur
        ResponseEntity<ZChercheur> response = zchercheurController.detailZChercheur(1);

        // Vérifie que le statut de la réponse est 200 (OK)
        assertEquals(200, response.getStatusCodeValue());

        // Vérifie que le chercheur retourné est le bon
        assertEquals(zchercheur, response.getBody());

        // Vérifie que la méthode findById du dépôt a été appelée une fois
        verify(zchercheurRepository, times(1)).findById(1);
    }

    // Test pour la méthode modifierZChercheur
    @Test
    void testModifierZChercheur() {
        // Crée une instance de ZChercheur pour le test
        ZChercheur zchercheur = new ZChercheur();
        zchercheur.setIdche(1);

        // Configure le comportement attendu du dépôt
        when(zchercheurRepository.findById(1)).thenReturn(Optional.of(zchercheur));

        // Crée une instance de ZChercheur mise à jour pour le test
        ZChercheur updatedZChercheur = new ZChercheur();
        updatedZChercheur.setNom("Updated Nom");

        // Appelle la méthode modifierZChercheur
        ResponseEntity<ZChercheur> response = zchercheurController.modifierZChercheur(1, updatedZChercheur);

        // Vérifie que le statut de la réponse est 200 (OK)
        assertEquals(200, response.getStatusCodeValue());

        // Vérifie que la méthode findById du dépôt a été appelée une fois
        verify(zchercheurRepository, times(1)).findById(1);

        // Vérifie que la méthode save du dépôt a été appelée une fois
        verify(zchercheurRepository, times(1)).save(zchercheur);
    }
}
