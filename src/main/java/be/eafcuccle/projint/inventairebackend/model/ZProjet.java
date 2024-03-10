package be.eafcuccle.projint.inventairebackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "ZProjet")
public class ZProjet {

    @Id
    @Column(name = "Idprojet", nullable = false)
    private int idprojet;

    @Column(name = "Nom", length = 255, nullable = false)
    private String nom;

    @Column(name = "NomUK", length = 255, nullable = false)
    private String nomUK;

    @Column(name = "Nomprogramme", length = 100, nullable = false)
    private String nomprogramme;

    @Column(name = "NomprogrammeUK", length = 100, nullable = false)
    private String nomprogrammeUK;

    @Column(name = "Resume", length = 2500, nullable = false)
    private String resume;

    @Column(name = "ResumeUK", length = 2500, nullable = false)
    private String resumeUK;

    @Column(name = "Datedebut", nullable = false)
    private Date datedebut;

    @Column(name = "Datefin", nullable = false)
    private Date datefin;

    @Column(name = "Datemaj", nullable = false)
    private Date datemaj;

    @Column(name = "Ordre", nullable = false)
    private int ordre;

    @Column(name = "Site", length = 255, nullable = false)
    private String site;

    @Column(name = "DDebut", length = 100, nullable = false)
    private String dDebut;

    @Column(name = "DFin", length = 100, nullable = false)
    private String dFin;

    @Column(name = "FromCv", length = 25, nullable = false, columnDefinition = "varchar(25) default '0'")
    private String fromCv;

    // Getters et setters avec data


    public ZProjet() {
    }

    public ZProjet(String nom, String nomUK, String nomprogramme, String nomprogrammeUK, String resume, String resumeUK, Date datedebut, Date datefin, Date datemaj, int ordre, String site, String dDebut, String dFin, String fromCv) {
        this.nom = nom;
        this.nomUK = nomUK;
        this.nomprogramme = nomprogramme;
        this.nomprogrammeUK = nomprogrammeUK;
        this.resume = resume;
        this.resumeUK = resumeUK;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.datemaj = datemaj;
        this.ordre = ordre;
        this.site = site;
        this.dDebut = dDebut;
        this.dFin = dFin;
        this.fromCv = fromCv;
    }

    public ZProjet(int idprojet, String nom, String nomUK, String nomprogramme, String nomprogrammeUK, String resume, String resumeUK, Date datedebut, Date datefin, Date datemaj, int ordre, String site, String dDebut, String dFin, String fromCv) {
        this.idprojet = idprojet;
        this.nom = nom;
        this.nomUK = nomUK;
        this.nomprogramme = nomprogramme;
        this.nomprogrammeUK = nomprogrammeUK;
        this.resume = resume;
        this.resumeUK = resumeUK;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.datemaj = datemaj;
        this.ordre = ordre;
        this.site = site;
        this.dDebut = dDebut;
        this.dFin = dFin;
        this.fromCv = fromCv;
    }
}
