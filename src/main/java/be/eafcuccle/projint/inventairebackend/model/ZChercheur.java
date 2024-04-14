package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "ZChercheur")
public class ZChercheur {

    @Id
    @Column(name = "Idche", nullable = false)
    private int idche;

    @Column(name = "Nom", length = 25, nullable = false)
    private String nom;

    @Column(name = "Prenom", length = 25, nullable = false)
    private String prenom;

    @Column(name = "Titre", length = 25)
    private String titre;

    @Column(name = "Matricule", length = 25)
    private String matricule;

    @Column(name = "CPI", length = 8)
    private String cpi;

    @Column(name = "Telephone", length = 25)
    private String telephone;

    @Column(name = "Email", length = 120, nullable = false)
    private String email;

    @Column(name = "Fax", length = 25)
    private String fax;

    @Column(name = "Site", length = 255, nullable = false)
    private String site;

    @Column(name = "Corps", length = 20, nullable = false)
    private String corps;

    @Column(name = "CorpsOrdre", nullable = false)
    private int corpsOrdre;

    @Column(name = "DDig", nullable = false, columnDefinition = "datetime default current_timestamp()")
    private Date dDig;

    @Column(name = "FacChe", length = 50, nullable = false, columnDefinition = "varchar(50) default ' '")
    private String facChe;

    @Column(name = "PrefPublication", length = 5000, columnDefinition = "varchar(5000) default 'integree'")
    private String prefPublication;

    @OneToMany(mappedBy = "zchercheur")
    private List<ZUCompos> composList;



    // Getters et setters avec data


    public ZChercheur() {
    }

    public ZChercheur(String nom, String prenom, String titre, String matricule, String cpi, String telephone, String email, String fax, String site, String corps, int corpsOrdre, Date dDig, String facChe, String prefPublication) {
        this.nom = nom;
        this.prenom = prenom;
        this.titre = titre;
        this.matricule = matricule;
        this.cpi = cpi;
        this.telephone = telephone;
        this.email = email;
        this.fax = fax;
        this.site = site;
        this.corps = corps;
        this.corpsOrdre = corpsOrdre;
        this.dDig = dDig;
        this.facChe = facChe;
        this.prefPublication = prefPublication;
    }

    public ZChercheur(int idche, String nom, String prenom, String titre, String matricule, String cpi, String telephone, String email, String fax, String site, String corps, int corpsOrdre, Date dDig, String facChe, String prefPublication) {
        this.idche = idche;
        this.nom = nom;
        this.prenom = prenom;
        this.titre = titre;
        this.matricule = matricule;
        this.cpi = cpi;
        this.telephone = telephone;
        this.email = email;
        this.fax = fax;
        this.site = site;
        this.corps = corps;
        this.corpsOrdre = corpsOrdre;
        this.dDig = dDig;
        this.facChe = facChe;
        this.prefPublication = prefPublication;
    }
}