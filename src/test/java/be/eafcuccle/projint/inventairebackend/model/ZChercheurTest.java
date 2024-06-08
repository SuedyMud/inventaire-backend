package be.eafcuccle.projint.inventairebackend.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ZChercheurTest {

    private ZChercheur chercheur;

    @BeforeEach
    public void setUp(){
        chercheur = new ZChercheur();
        chercheur.setNom("Dupont");
        chercheur.setPrenom("Jean");
        chercheur.setTitre("Dr");
        chercheur.setMatricule("12345");
        chercheur.setCpi("CPI123");
        chercheur.setTelephone("+32 498123456");
        chercheur.setEmail("jean.dupont@example.com");
        chercheur.setFax("12345");
        chercheur.setSite("Site Example");
        chercheur.setCorps("Scientifique");
        chercheur.setCorpsOrdre(1);
        chercheur.setDDig(new Date());
        chercheur.setFacChe("Faculté de Sciences");
        chercheur.setPrefPublication("Préférence de publication");
    }

    @Test
    public void testGettersSetters() {
        assertEquals("Dupont", chercheur.getNom());
        assertEquals("Jean", chercheur.getPrenom());
        assertEquals("Dr", chercheur.getTitre());
        assertEquals("12345", chercheur.getMatricule());
        assertEquals("CPI123", chercheur.getCpi());
        assertEquals("+32 498123456", chercheur.getTelephone());
        assertEquals("jean.dupont@example.com", chercheur.getEmail());
        assertEquals("12345", chercheur.getFax());
        assertEquals("Site Example", chercheur.getSite());
        assertEquals("Scientifique", chercheur.getCorps());
        assertEquals(1, chercheur.getCorpsOrdre());
        assertNotNull(chercheur.getDDig());
        assertEquals("Faculté de Sciences", chercheur.getFacChe());
        assertEquals("Préférence de publication", chercheur.getPrefPublication());
    }

    @Test
    public void testEqualsAndHashCode() {
        ZChercheur chercheur1 = new ZChercheur();
        chercheur1.setIdche(1);
        ZChercheur chercheur2 = new ZChercheur();
        chercheur2.setIdche(1);

        assertEquals(chercheur1, chercheur2);
        assertEquals(chercheur1.hashCode(), chercheur2.hashCode());

        chercheur2.setIdche(2);
        assertNotEquals(chercheur1, chercheur2);
        assertNotEquals(chercheur1.hashCode(), chercheur2.hashCode());
    }

    @Test
    public void testToString() {
        String toString = chercheur.toString();
        assertTrue(toString.contains("Dupont"));
        assertTrue(toString.contains("Jean"));
        assertTrue(toString.contains("Dr"));
    }

}