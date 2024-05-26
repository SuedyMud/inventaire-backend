package be.eafcuccle.projint.inventairebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "ZUnite")
public class ZUnite {

    @Id
    @Column(name = "Idunite", length = 10, nullable = false)
    private String idunite;

    @Column(name = "Istrans", nullable = false)
    private int istrans;

    @Column(name = "Preflang", length = 10, nullable = false)
    private String preflang;

    @Column(name = "Nom", length = 120, nullable = false)
    private String nom;

    @Column(name = "NomUK", length = 120, nullable = false)
    private String nomUK;

    @Column(name = "Sigle", length = 20, nullable = false)
    private String sigle;

    @Column(name = "Description", length = 2500, nullable = false)
    private String description;

    @Column(name = "DescriptionUK", length = 2500, nullable = false)
    private String descriptionUK;

    @Column(name = "Rue", length = 80, nullable = false)
    private String rue;

    @Column(name = "Numero", length = 10, nullable = false)
    private String numero;

    @Column(name = "Boite", length = 10, nullable = false)
    private String boite;

    @Column(name = "Localite", length = 80, nullable = false)
    private String localite;

    @Column(name = "Codepostal", length = 10, nullable = false)
    private String codepostal;

    @Column(name = "CPI", length = 10, nullable = false)
    private String cpi;

    @Column(name = "Localisation", length = 80, nullable = false)
    private String localisation;

    @Column(name = "Telephone", length = 30, nullable = false)
    private String telephone;

    @Column(name = "Fax", length = 30, nullable = false)
    private String fax;

    @Column(name = "Email", length = 120, nullable = false)
    private String email;

    @Column(name = "Site1", length = 120, nullable = false)
    private String site1;

    @Column(name = "Site2", length = 120, nullable = false)
    private String site2;

    @Column(name = "Lienthese", length = 120, nullable = false)
    private String lienthese;

    @Column(name = "Lienpublica", length = 120, nullable = false)
    private String lienpublica;

    @Column(name = "Datedeb", nullable = false)
    private Date datedeb;

    @Column(name = "Datefin", nullable = false)
    private Date datefin;

    @Column(name = "Datemaj", nullable = false)
    private Date datemaj;

    @Column(name = "Remarque", length = 500, nullable = false)
    private String remarque;

    @Column(name = "Nbvisit", nullable = false)
    private int nbvisit;

    @Column(name = "Brouillon", length = 500, nullable = false)
    private String brouillon;

    @Column(name = "PrefPublication", length = 5000, columnDefinition = "varchar(5000) default 'integree'")
    private String prefPublication;

    @Column(name = "statExport", nullable = false, columnDefinition = "int default 0")
    private int statExport;

    @Column(name = "statProjetcv", nullable = false, columnDefinition = "int default 0")
    private int statProjetcv;

    @Column(name = "statAnciensmembres", nullable = false, columnDefinition = "int default 0")
    private int statAnciensmembres;

    @Column(name = "statDelegue", nullable = false, columnDefinition = "int default 0")
    private int statDelegue;

    @Column(name = "statAdzion", nullable = false, columnDefinition = "int default 0")
    private int statAdzion;

    @Column(name = "Niveau", length = 10, columnDefinition = "varchar(10) default null comment 'Dans repertoire : 2 => niveau SFI, 3 => niveau unité'")
    private String niveau;

    @JsonIgnore
    @OneToMany(mappedBy = "zunite")
    private List<ZUCompos> composList;
/*
    @JsonIgnore
    @OneToMany(mappedBy = "refunite")
    private List<ZUProjet> zuProjetList;*/

    // Getters et setters avec data


    public ZUnite() {
    }

}