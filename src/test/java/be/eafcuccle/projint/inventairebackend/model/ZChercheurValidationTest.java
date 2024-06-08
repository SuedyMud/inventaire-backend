package be.eafcuccle.projint.inventairebackend.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZChercheurValidationTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testNomNotEmpty() {
        ZChercheur chercheur = new ZChercheur();
        chercheur.setNom("");
        chercheur.setPrenom("Jean");
        chercheur.setTelephone("+32 498123456");
        chercheur.setEmail("jean.dupont@example.com");
        chercheur.setSite("Site Example");
        chercheur.setCorps("Scientifique");
        chercheur.setCorpsOrdre(1);
        chercheur.setDDig(new Date());
        chercheur.setFacChe("Faculté de Sciences");

        Set<ConstraintViolation<ZChercheur>> violations = validator.validate(chercheur);
        assertEquals(1, violations.size());
        assertTrue(violations.iterator().next().getMessage().contains("doit être rempli"));
    }

    @Test
    public void testNomPattern() {
        ZChercheur chercheur = new ZChercheur();
        chercheur.setNom("Invalid@Name");
        chercheur.setPrenom("Jean");
        chercheur.setTelephone("+32 498123456");
        chercheur.setEmail("jean.dupont@example.com");
        chercheur.setSite("Site Example");
        chercheur.setCorps("Scientifique");
        chercheur.setCorpsOrdre(1);
        chercheur.setDDig(new Date());
        chercheur.setFacChe("Faculté de Sciences");

        Set<ConstraintViolation<ZChercheur>> violations = validator.validate(chercheur);
        assertEquals(1, violations.size());
        assertTrue(violations.iterator().next().getMessage().contains("Nom: lettres seulement, 25 caractères max."));
    }

    @Test
    public void testPrenomNotEmpty() {
        ZChercheur chercheur = new ZChercheur();
        chercheur.setNom("Dupont");
        chercheur.setPrenom("");
        chercheur.setTelephone("+32 498123456");
        chercheur.setEmail("jean.dupont@example.com");
        chercheur.setSite("Site Example");
        chercheur.setCorps("Scientifique");
        chercheur.setCorpsOrdre(1);
        chercheur.setDDig(new Date());
        chercheur.setFacChe("Faculté de Sciences");

        Set<ConstraintViolation<ZChercheur>> violations = validator.validate(chercheur);
        assertEquals(1, violations.size());
        assertTrue(violations.iterator().next().getMessage().contains("doit être rempli"));
    }

    @Test
    public void testPrenomPattern() {
        ZChercheur chercheur = new ZChercheur();
        chercheur.setNom("Dupont");
        chercheur.setPrenom("Invalid@Name");
        chercheur.setTelephone("+32 498123456");
        chercheur.setEmail("jean.dupont@example.com");
        chercheur.setSite("Site Example");
        chercheur.setCorps("Scientifique");
        chercheur.setCorpsOrdre(1);
        chercheur.setDDig(new Date());
        chercheur.setFacChe("Faculté de Sciences");

        Set<ConstraintViolation<ZChercheur>> violations = validator.validate(chercheur);
        assertEquals(1, violations.size());
        assertTrue(violations.iterator().next().getMessage().contains("Prénom: lettres seulement, 25 caractères max."));
    }

    @Test
    public void testTelephoneNotEmpty() {
        ZChercheur chercheur = new ZChercheur();
        chercheur.setNom("Dupont");
        chercheur.setPrenom("Jean");
        chercheur.setTelephone("");
        chercheur.setEmail("jean.dupont@example.com");
        chercheur.setSite("Site Example");
        chercheur.setCorps("Scientifique");
        chercheur.setCorpsOrdre(1);
        chercheur.setDDig(new Date());
        chercheur.setFacChe("Faculté de Sciences");

        Set<ConstraintViolation<ZChercheur>> violations = validator.validate(chercheur);
        assertEquals(1, violations.size());
        assertTrue(violations.iterator().next().getMessage().contains("doit être rempli"));
    }

    @Test
    public void testTelephonePattern() {
        ZChercheur chercheur = new ZChercheur();
        chercheur.setNom("Dupont");
        chercheur.setPrenom("Jean");
        chercheur.setTelephone("InvalidPhone");
        chercheur.setEmail("jean.dupont@example.com");
        chercheur.setSite("Site Example");
        chercheur.setCorps("Scientifique");
        chercheur.setCorpsOrdre(1);
        chercheur.setDDig(new Date());
        chercheur.setFacChe("Faculté de Sciences");

        Set<ConstraintViolation<ZChercheur>> violations = validator.validate(chercheur);
        assertEquals(1, violations.size());
        assertTrue(violations.iterator().next().getMessage().contains("Téléphone: format +32 4XXXXXXX."));
    }

    @Test
    public void testEmailNotEmpty() {
        ZChercheur chercheur = new ZChercheur();
        chercheur.setNom("Dupont");
        chercheur.setPrenom("Jean");
        chercheur.setTelephone("+32 498123456");
        chercheur.setEmail("");
        chercheur.setSite("Site Example");
        chercheur.setCorps("Scientifique");
        chercheur.setCorpsOrdre(1);
        chercheur.setDDig(new Date());
        chercheur.setFacChe("Faculté de Sciences");

        Set<ConstraintViolation<ZChercheur>> violations = validator.validate(chercheur);
        assertEquals(1, violations.size());
        assertTrue(violations.iterator().next().getMessage().contains("doit être rempli"));
    }

    @Test
    public void testEmailPattern() {
        ZChercheur chercheur = new ZChercheur();
        chercheur.setNom("Dupont");
        chercheur.setPrenom("Jean");
        chercheur.setTelephone("+32 498123456");
        chercheur.setEmail("invalid-email");
        chercheur.setSite("Site Example");
        chercheur.setCorps("Scientifique");
        chercheur.setCorpsOrdre(1);
        chercheur.setDDig(new Date());
        chercheur.setFacChe("Faculté de Sciences");

        Set<ConstraintViolation<ZChercheur>> violations = validator.validate(chercheur);
        assertEquals(1, violations.size());
        assertTrue(violations.iterator().next().getMessage().contains("E-mail invalide"));
    }

    // Ajoutez des tests pour les autres champs et leurs contraintes si nécessaire
}
