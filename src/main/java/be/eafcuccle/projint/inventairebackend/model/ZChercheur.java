package be.eafcuccle.projint.inventairebackend.model;


import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;


@Data
@Entity
@Table(name = "ZChercheur")
public class ZChercheur {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Idche", length = 10, nullable = false)
    private Integer idche;

    @NotEmpty
    //@Pattern(regexp = "\"^[A-Za-zÀ-ÿ\\s'’.,\\-]{1,120}$\"", message = "Nom: lettres seulement, 25 caractères max.")
    @Column(name = "Nom", length = 25, nullable = false)
    private String nom;

    @NotEmpty
    //@Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]{1,25}$", message = "Prénom: lettres seulement, 25 caractères max.")
    @Column(name = "Prenom", length = 25, nullable = false)
    private String prenom;

    //@Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]{0,25}$", message = "Titre: lettres seulement, 25 caractères max.")
    @Column(name = "Titre", length = 25)
    private String titre;

    //@Pattern(regexp = "^[A-Za-z0-9]{0,25}$", message = "Matricule: 25 caractères max.")
    @Column(name = "Matricule", length = 25)
    private String matricule;

   // @Pattern(regexp = "^[A-Za-z0-9]{0,8}$", message = "CPI: 8 caractères max.")
    @Column(name = "CPI", length = 8)
    private String cpi;

    @NotEmpty
   // @Pattern(regexp = "\\+\\d{2} \\d{9}", message = "Téléphone: format +32 4XXXXXXX.")
    @Column(name = "Telephone", length = 25)
    private String telephone;

    @NotEmpty
   // @Pattern(regexp = "^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "E-mail invalide")
    @Column(name = "Email", length = 120, nullable = false)
    private String email;

    //@Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]{0,25}$", message = "Fax: lettres seulement, 25 caractères max.")
    @Column(name = "Fax", length = 25)
    private String fax;

    //@Pattern(regexp = "^[A-Za-zÀ-ÿ0-9\\s]{1,255}$", message = "Site: 255 caractères max.")
    @Column(name = "Site", length = 255, nullable = false)
    private String site;

    //@Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]{1,20}$", message = "Corps: lettres seulement, 20 caractères max.")
    @Column(name = "Corps", length = 20, nullable = false)
    private String corps;

    @Column(name = "CorpsOrdre", nullable = false)
    private int corpsOrdre;

    @Column(name = "DDig", nullable = false, columnDefinition = "datetime default current_timestamp()")
    private Date dDig;

    //@Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]{1,50}$", message = "FacChe: lettres seulement, 50 caractères max.")
    @Column(name = "FacChe", length = 50, nullable = false, columnDefinition = "varchar(50) default ' '")
    private String facChe;

    //@Pattern(regexp = "^[A-Za-zÀ-ÿ0-9\\s]{0,5000}$", message = "PrefPublication: 5000 caractères max.")
    @Column(name = "PrefPublication", length = 5000, columnDefinition = "varchar(5000) default 'integree'")
    private String prefPublication;


    // Getters et setters avec data


    public ZChercheur() {
    }

    // Ajoutez cette méthode pour générer un nouvel identifiant



}