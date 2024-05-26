package be.eafcuccle.projint.inventairebackend.controller;

import be.eafcuccle.projint.inventairebackend.model.ZUCompos;
import be.eafcuccle.projint.inventairebackend.model.ZUnite;
import be.eafcuccle.projint.inventairebackend.repository.ZUniteRepository;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ZUniteControllerTest {

    @Mock
    private ZUniteRepository zuniteRepository;

    @InjectMocks
    private ZUniteController zuniteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test pour la méthode ajouterZUnite
    @Test
    void testAjouterZUnite() {
        // Crée une instance de ZUnite pour le test
        ZUnite zunite = new ZUnite();
        zunite.setNom("Test Unite");

        // Prépare l'UriComponentsBuilder pour le test
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("");

        // Configure le comportement attendu du dépôt
        when(zuniteRepository.save(any(ZUnite.class))).thenReturn(zunite);

        // Appelle la méthode ajouterZUnite
        ResponseEntity<?> response = zuniteController.ajouterZUnite(zunite, builder);

        // Vérifie que le statut de la réponse est 201 (Created)
        assertEquals(201, response.getStatusCodeValue());

        // Vérifie que la méthode save du dépôt a été appelée une fois
        verify(zuniteRepository, times(1)).save(zunite);
    }

    // Test pour la méthode listeZUnites
    @Test
    void testListeZUnites() {
        // Prépare un objet Pageable et une page simulée contenant une liste d'unités
        Pageable pageable = PageRequest.of(0, 10);
        Page<ZUnite> page = new PageImpl<>(Collections.singletonList(new ZUnite()));

        // Configure le comportement attendu du dépôt
        when(zuniteRepository.findAll(pageable)).thenReturn(page);

        // Appelle la méthode listeZUnites
        Page<ZUnite> result = zuniteController.listeZUnites(pageable);

        // Vérifie que la taille de la page retournée est correcte
        assertEquals(1, result.getTotalElements());

        // Vérifie que la méthode findAll du dépôt a été appelée une fois
        verify(zuniteRepository, times(1)).findAll(pageable);
    }

    // Test pour la méthode getComposOfUnite
    @Test
    void testGetComposOfUnite() {
        // Crée une instance de ZUnite pour le test
        ZUnite zunite = new ZUnite();
        zunite.setIdunite("1");

        // Configure le comportement attendu du dépôt
        when(zuniteRepository.findById("1")).thenReturn(Optional.of(zunite));

        // Appelle la méthode getComposOfUnite
        ResponseEntity<List<ZUCompos>> response = zuniteController.getComposOfUnite("1");

        // Vérifie que le statut de la réponse est 200 (OK)
        assertEquals(200, response.getStatusCodeValue());

        // Vérifie que la liste des composants retournée est vide (dans ce cas de test)
        assertTrue(response.getBody().isEmpty());

        // Vérifie que la méthode findById du dépôt a été appelée une fois
        verify(zuniteRepository, times(1)).findById("1");
    }

    // Test pour la méthode supprimerZUnite
    @Test
    void testSupprimerZUnite() {
        // Configure le comportement attendu du dépôt
        when(zuniteRepository.existsById("1")).thenReturn(true);

        // Appelle la méthode supprimerZUnite
        ResponseEntity<?> response = zuniteController.supprimerZUnite("1");

        // Vérifie que le statut de la réponse est 204 (No Content)
        assertEquals(204, response.getStatusCodeValue());

        // Vérifie que la méthode deleteById du dépôt a été appelée une fois
        verify(zuniteRepository, times(1)).deleteById("1");
    }

    // Test pour la méthode detailZUnite
    @Test
    void testDetailZUnite() {
        // Crée une instance de ZUnite pour le test
        ZUnite zunite = new ZUnite();
        zunite.setIdunite("1");

        // Configure le comportement attendu du dépôt
        when(zuniteRepository.findById("1")).thenReturn(Optional.of(zunite));

        // Appelle la méthode detailZUnite
        ResponseEntity<ZUnite> response = zuniteController.detailZUnite("1");

        // Vérifie que le statut de la réponse est 200 (OK)
        assertEquals(200, response.getStatusCodeValue());

        // Vérifie que l'unité retournée est la bonne
        assertEquals(zunite, response.getBody());

        // Vérifie que la méthode findById du dépôt a été appelée une fois
        verify(zuniteRepository, times(1)).findById("1");
    }

    // Test pour la méthode modifierZUnite
    @Test
    void testModifierZUnite() {
        // Crée une instance de ZUnite pour le test
        ZUnite zunite = new ZUnite();
        zunite.setIdunite("1");

        // Configure le comportement attendu du dépôt
        when(zuniteRepository.findById("1")).thenReturn(Optional.of(zunite));

        // Crée une instance de ZUnite mise à jour pour le test
        ZUnite updatedZUnite = new ZUnite();
        updatedZUnite.setNom("Updated Nom");

        // Appelle la méthode modifierZUnite
        ResponseEntity<ZUnite> response = zuniteController.modifierZUnite("1", updatedZUnite);

        // Vérifie que le statut de la réponse est 200 (OK)
        assertEquals(200, response.getStatusCodeValue());

        // Vérifie que la méthode findById du dépôt a été appelée une fois
        verify(zuniteRepository, times(1)).findById("1");

        // Vérifie que la méthode save du dépôt a été appelée une fois
        verify(zuniteRepository, times(1)).save(zunite);
    }
}
