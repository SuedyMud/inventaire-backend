package be.eafcuccle.projint.inventairebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "ZChercheur")
public class ZChercheur {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    //@JsonManagedReference // Cette annotation aide Jackson à sérialiser cette propriété
    @JsonIgnore
    private List<ZUCompos> composList;


    // Getters et setters avec data


    public ZChercheur() {
    }


}