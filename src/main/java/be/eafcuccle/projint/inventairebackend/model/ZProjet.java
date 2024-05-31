package be.eafcuccle.projint.inventairebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "zprojet")
public class ZProjet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "DDebut", length = 100/*, nullable = false*/)
    private String dDebut;

    @Column(name = "DFin", length = 100/*, nullable = false*/)
    private String dFin;

    @Column(name = "FromCv", length = 25, /*nullable = false,*/ columnDefinition = "varchar(25) default '0'")
    private String fromCv;

    @OneToMany(mappedBy = "zprojet")
    Set<ZUProjet> zuprojet;


    public ZProjet() {
    }

    // Getters et setters avec data


}
